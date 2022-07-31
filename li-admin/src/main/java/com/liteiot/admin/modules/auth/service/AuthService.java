package com.liteiot.admin.modules.auth.service;


import com.liteiot.admin.modules.auth.util.user.JwtAuthenticationRequest;
import com.liteiot.api.vo.user.PwdEntity;

import java.util.Map;

/**
 * 授权服务
 */
public interface AuthService {

    /**
     * 执行登录操作
     *
     * @param authenticationRequest
     * @return
     * @throws Exception
     */
    Map login(JwtAuthenticationRequest authenticationRequest) throws Exception;

    /**
     * 刷新token
     *
     * @param oldToken
     * @return
     * @throws Exception
     */
    String refresh(String oldToken) throws Exception;

    /**
     * 验证token是否有效
     *
     * @param token
     * @throws Exception
     */
    void validate(String token) throws Exception;

    /**
     * 退出登录
     *
     * @param token
     * @throws Exception
     */
    void logout(String token) throws Exception;

    /**
     * 用户修改密码
     *
     * @param pwd
     */
    void changePassword(String token, PwdEntity pwd) throws Exception;
}
