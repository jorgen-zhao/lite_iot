package com.liteiot.admin.modules.auth.service.impl;

import com.alibaba.fastjson.JSON;
import com.liteiot.admin.modules.admin.biz.UserBiz;
import com.liteiot.admin.modules.admin.entity.OnlineLog;
import com.liteiot.admin.modules.admin.rpc.service.PermissionService;
import com.liteiot.admin.modules.auth.service.AuthService;
import com.liteiot.admin.modules.auth.util.user.JwtAuthenticationRequest;
import com.liteiot.admin.modules.auth.util.user.JwtTokenUtil;
import com.liteiot.api.vo.user.PwdEntity;
import com.liteiot.api.vo.user.UserInfo;
import com.liteiot.common.constant.RedisKeyConstant;
import com.liteiot.common.exception.auth.UserInvalidException;
import com.liteiot.common.util.AddressUtils;
import com.liteiot.common.util.IpUtils;
import com.liteiot.common.util.WebUtils;
import com.liteiot.common.util.jwt.IJWTInfo;
import com.liteiot.common.util.jwt.JWTInfo;
import eu.bitwalker.useragentutils.UserAgent;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private UserBiz userBiz;

    @Value("${jwt.expire}")
    private int expire;


    @Override
    public Map login(JwtAuthenticationRequest authenticationRequest) throws Exception {
        UserInfo info = permissionService.validate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        if (StringUtils.isNotBlank(info.getId())) {
            JWTInfo jwtInfo = new JWTInfo(info.getUsername(), info.getId() + "", info.getName());
            String token = jwtTokenUtil.generateToken(jwtInfo);
            Map<String, String> result = new HashMap<>();
            result.put("accessToken", token);
            result.put("id", info.id);
            WebUtils.getRequest();
            writeOnlineLog(jwtInfo);
            // 每次登录时，重新缓存用户与组织树操作公共方法
            userBiz.cacheUserGroupIds(info.getUsername());
            return result;
        }
        throw new UserInvalidException("用户不存在或账户密码错误!");
    }

    @Override
    public void validate(String token) throws Exception {
        jwtTokenUtil.getInfoFromToken(token);
    }

    @Override
    public String refresh(String oldToken) throws Exception {
        IJWTInfo infoFromToken = jwtTokenUtil.getInfoFromToken(oldToken);
        String token = jwtTokenUtil.generateToken(infoFromToken);
        // 刷新token，将token存入redis，过期时间为expire分钟
        stringRedisTemplate.expire(RedisKeyConstant.REDIS_KEY_TOKEN + ":" + infoFromToken.getTokenId(), expire, TimeUnit.MINUTES);
        return token;
    }

    @Override
    public void logout(String token) throws Exception {
        IJWTInfo infoFromToken = jwtTokenUtil.getInfoFromToken(token);
        String tokenId = infoFromToken.getTokenId();
        String username = infoFromToken.getUniqueName();
        stringRedisTemplate.delete(RedisKeyConstant.REDIS_KEY_TOKEN + ":" + tokenId);
        stringRedisTemplate.opsForZSet().remove(RedisKeyConstant.REDIS_KEY_TOKEN, tokenId);
        // 退出登陆时同时清理权限
        String key = String.format(RedisKeyConstant.REDIS_KEY_USER_PERMISSION, username);
        stringRedisTemplate.delete(key);
    }

    @Override
    public void changePassword(String token, PwdEntity pwd) throws Exception {
        IJWTInfo infoFromToken = jwtTokenUtil.getInfoFromToken(token);
        String username = infoFromToken.getUniqueName();
        if (StringUtils.isBlank(username)) {
            throw new UserInvalidException("用户不存在!");
        }
        UserInfo info = permissionService.validate(username, pwd.getOldPwd());
        String newPwd = pwd.getNewPwd();
        userBiz.changePassword(info.getUsername(), newPwd);
    }

    @Async
    public void writeOnlineLog(JWTInfo jwtInfo) {
        final UserAgent userAgent = UserAgent.parseUserAgentString(WebUtils.getRequest().getHeader("User-Agent"));
        final String ip = IpUtils.getRemoteIP(WebUtils.getRequest());
        String address = AddressUtils.getRealAddressByIP(ip);

        OnlineLog onlineLog = new OnlineLog();
        // 获取客户端操作系统
        String os = userAgent.getOperatingSystem().getName();
        // 获取客户端浏览器
        String browser = userAgent.getBrowser().getName();
        onlineLog.setBrowser(browser);
        onlineLog.setIpaddr(ip);
        onlineLog.setTokenId(jwtInfo.getTokenId());
        onlineLog.setLoginTime(System.currentTimeMillis());
        onlineLog.setUserId(jwtInfo.getId());
        onlineLog.setUserName(jwtInfo.getName());
        onlineLog.setLoginLocation(address);
        onlineLog.setOs(os);
        stringRedisTemplate.opsForValue().set(RedisKeyConstant.REDIS_KEY_TOKEN + ":" + jwtInfo.getTokenId(), JSON.toJSONString(onlineLog, false), expire, TimeUnit.MINUTES);
        stringRedisTemplate.opsForZSet().add((RedisKeyConstant.REDIS_KEY_TOKEN), jwtInfo.getTokenId(), 0);
    }
}
