package com.liteiot.gate.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.liteiot.auth.client.config.UserAuthConfig;
import com.liteiot.auth.client.jwt.UserAuthUtil;
import com.liteiot.api.vo.authority.CheckPermissionInfo;
import com.liteiot.api.vo.authority.PermissionInfo;
import com.liteiot.api.vo.log.LogInfo;
import com.liteiot.common.constant.RedisKeyConstant;
import com.liteiot.common.context.BaseContextHandler;
import com.liteiot.common.exception.auth.UserTokenException;
import com.liteiot.common.msg.BaseResponse;
import com.liteiot.common.msg.auth.TokenForbiddenResponse;
import com.liteiot.common.util.http.IPUtil;
import com.liteiot.common.util.jwt.IJWTInfo;
import com.liteiot.gate.handler.RequestBodyRoutePredicateFactory;
import com.liteiot.gate.service.LogService;
import com.liteiot.gate.utils.DBLog;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

/**
 * 过滤器
 */
@Configuration
@Slf4j
public class AccessGatewayFilter implements GlobalFilter {
    @Autowired
    private LogService logService;

    @Value("${gate.ignore.startWith}")
    private String startWith;

    private static final String GATE_WAY_PREFIX = "/api";

    @Autowired
    private UserAuthUtil userAuthUtil;

    @Autowired
    private UserAuthConfig userAuthConfig;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Mono<Void> filter(ServerWebExchange serverWebExchange, GatewayFilterChain gatewayFilterChain) {
        LinkedHashSet requiredAttribute = serverWebExchange.getRequiredAttribute(ServerWebExchangeUtils.GATEWAY_ORIGINAL_REQUEST_URL_ATTR);
        ServerHttpRequest request = serverWebExchange.getRequest();
        // 获取当前网关访问的URI
        String requestUri = request.getPath().pathWithinApplication().value();
        if (requiredAttribute != null) {
            Iterator<URI> iterator = requiredAttribute.iterator();
            while (iterator.hasNext()) {
                URI next = iterator.next();
                if (next.getPath().startsWith(GATE_WAY_PREFIX)) {
                    requestUri = next.getPath().substring(GATE_WAY_PREFIX.length());
                }
            }
        }
        final String method = request.getMethod().toString();
        BaseContextHandler.setToken(null);
        ServerHttpRequest.Builder mutate = request.mutate();
        // 网关不进行拦截的URI配置，常见如验证码、Login接口
        if (isStartWith(requestUri)) {
            ServerHttpRequest build = mutate.build();
            return gatewayFilterChain.filter(serverWebExchange.mutate().request(build).build());
        }
        IJWTInfo user;
        try {
            // 判断用户token，获取用户信息
            user = getJWTUser(request, mutate);
        } catch (Exception e) {
            log.error("用户Token过期异常", e);
            return getVoidMono(serverWebExchange, new TokenForbiddenResponse("User Token Error or Expired!"), HttpStatus.UNAUTHORIZED);
        }

        // 构建远程请求接口，获取用户权限信息
        Mono<CheckPermissionInfo> checkPermissionInfoMono = webClientBuilder.build()
                .get()
                .uri("http://li-admin/api/user/{username}/check_permission?requestMethod=" + method + "&requestUri=" + requestUri, user.getUniqueName())
                .header(userAuthConfig.getTokenHeader(), BaseContextHandler.getToken())
                .retrieve()
                .bodyToMono(CheckPermissionInfo.class);

        IJWTInfo finalUser = user;
        return checkPermissionInfoMono.flatMap(checkPermissionInfo -> {
            // 当前用户具有访问权限
            if (checkPermissionInfo.getIsAuth()) {
                if (checkPermissionInfo.getPermissionInfo() != null) {
                    // 若资源存在则请求设置访问日志
                    setCurrentUserInfoAndLog(serverWebExchange, finalUser, checkPermissionInfo.getPermissionInfo());
                }
                ServerHttpRequest build = mutate.build();
                return gatewayFilterChain.filter(serverWebExchange.mutate().request(build).build());
            } else {
                // 当前用户不具有访问权限
                return getVoidMono(serverWebExchange, new TokenForbiddenResponse("Forbidden!Does not has Permission!"), HttpStatus.FORBIDDEN);
            }
        });
    }

    /**
     * 网关抛异常
     *
     * @param body
     */
    private Mono<Void> getVoidMono(ServerWebExchange serverWebExchange, BaseResponse body, HttpStatus status) {
        serverWebExchange.getResponse().setStatusCode(status);
        byte[] bytes = JSONObject.toJSONString(body).getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = serverWebExchange.getResponse().bufferFactory().wrap(bytes);
        return serverWebExchange.getResponse().writeWith(Flux.just(buffer));
    }


    /**
     * 设置当前用户信息与记录日志
     *
     * @param serverWebExchange
     * @param user
     * @param pm
     */
    private void setCurrentUserInfoAndLog(ServerWebExchange serverWebExchange, IJWTInfo user, PermissionInfo pm) {
        ServerHttpRequest request = serverWebExchange.getRequest();
//        String host = request.getRemoteAddress().toString();
        String host = IPUtil.getIP(request);
        String requestUri = request.getURI().getPath();
        String queryParam = request.getURI().getQuery();
        Map<String, Object> attributeMap = serverWebExchange.getAttributes();
        Object requestBody = attributeMap.get(RequestBodyRoutePredicateFactory.REQUEST_BODY_ATTR);
        MediaType contentType = request.getHeaders().getContentType();
        String requestBodyStr = null;
        if (contentType == null || !contentType.includes(MediaType.MULTIPART_FORM_DATA)) {// 只有文件上传使用了form-data,过滤掉文件上传的body参数
            requestBodyStr = requestBody == null ? queryParam : String.valueOf(requestBody);
        }
        LogInfo logInfo = new LogInfo(pm.getMenu(), pm.getName(), pm.getUri(), new Date(), user.getId(), user.getName(), host, requestBodyStr,requestUri,user.getTokenId());
        log.info("操作日志:{}", JSON.toJSONString(logInfo));
        DBLog.getInstance().setLogService(logService).offerQueue(logInfo);
    }

    /**
     * 返回session中的用户信息
     *
     * @param request
     * @param ctx
     * @return
     */
    private IJWTInfo getJWTUser(ServerHttpRequest request, ServerHttpRequest.Builder ctx) throws Exception {
        List<String> strings = request.getHeaders().get(userAuthConfig.getTokenHeader());
        String authToken = null;
        if (strings != null) {
            authToken = strings.get(0);
        }
        if (StringUtils.isBlank(authToken)) {
            strings = request.getQueryParams().get("token");
            if (strings != null) {
                authToken = strings.get(0);
            }
        }
        IJWTInfo infoFromToken = userAuthUtil.getInfoFromToken(authToken);
        String s = stringRedisTemplate.opsForValue().get(RedisKeyConstant.REDIS_KEY_TOKEN + ":" + infoFromToken.getTokenId());
        if (StringUtils.isBlank(s)) {
            throw new UserTokenException("User token expired!");
        }
        ctx.header(userAuthConfig.getTokenHeader(), authToken);
        BaseContextHandler.setToken(authToken);
        return infoFromToken;
    }


    /**
     * URI是否以什么打头
     *
     * @param requestUri
     * @return
     */
    private boolean isStartWith(String requestUri) {
        boolean flag = false;
        for (String s : startWith.split(",")) {
            if (requestUri.startsWith(s)) {
                return true;
            }
        }
        return flag;
    }


}
