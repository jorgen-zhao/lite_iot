package com.liteiot.admin.modules.auth.controller;

import com.liteiot.admin.modules.auth.service.AuthService;
import com.liteiot.admin.modules.auth.util.user.JwtAuthenticationRequest;
import com.liteiot.api.vo.user.PwdEntity;
import com.liteiot.common.exception.auth.UserInvalidException;
import com.liteiot.common.msg.ObjectRestResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

import static com.liteiot.common.constant.RedisKeyConstant.REDIS_KEY_CAPTCHA;

@RestController
@RequestMapping("jwt")
@Slf4j
public class AuthController {
    @Autowired
    protected HttpServletRequest request;

    @Value("${jwt.token-header}")
    private String tokenHeader;

    @Autowired
    private AuthService authService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * web登录接口
     *
     * @param authenticationRequest
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "token", method = RequestMethod.POST)
    public ObjectRestResponse<String> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest) throws Exception {

        log.info(authenticationRequest.getUsername() + " require logging...");
        // 获取session中的验证码
        String sessionCode = stringRedisTemplate.opsForValue().get(String.format(REDIS_KEY_CAPTCHA, authenticationRequest.getUuid()));
        if (sessionCode == null) {
            throw new UserInvalidException("验证码已过期");
        }
        // 判断验证码
        if (authenticationRequest.getVerCode() == null || !sessionCode.equals(authenticationRequest.getVerCode().trim().toLowerCase())) {
            throw new UserInvalidException("验证码不正确");
        }
        Map result = authService.login(authenticationRequest);

        return new ObjectRestResponse<>().data(result);
    }

    /**
     * 小程序登陆
     *
     * @param authenticationRequest
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "miniLogin", method = RequestMethod.POST)
    public ObjectRestResponse<String> miniLogin(@RequestBody JwtAuthenticationRequest authenticationRequest) throws Exception {
        log.info(authenticationRequest.getUsername() + " mini program require logging...");
        Map result = authService.login(authenticationRequest);

        return new ObjectRestResponse<>().data(result);
    }

    /**
     * 刷新token
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "refresh", method = RequestMethod.GET)
    public ObjectRestResponse<String> refreshAndGetAuthenticationToken(
            HttpServletRequest request) throws Exception {
        String token = request.getHeader(tokenHeader);
        String refreshedToken = authService.refresh(token);
        return new ObjectRestResponse<>().data(refreshedToken);
    }

    /**
     * 验证token
     *
     * @param token
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "verify", method = RequestMethod.GET)
    public ObjectRestResponse<?> verify(String token) throws Exception {
        authService.validate(token);
        return new ObjectRestResponse<>();
    }

    /**
     * 登出
     *
     * @param token
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "logout", method = RequestMethod.DELETE)
    public ObjectRestResponse<?> logout(String token) throws Exception {
        authService.logout(token);
        return new ObjectRestResponse<>();
    }

    /**
     * 用户修改密码
     *
     * @param pwd
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "changePassword", method = RequestMethod.POST)
    public ObjectRestResponse<?> changePassword( HttpServletRequest request, @RequestBody PwdEntity pwd) throws Exception {
        String token = request.getHeader(tokenHeader);
        authService.changePassword(token, pwd);
        return new ObjectRestResponse<>();
    }

}
