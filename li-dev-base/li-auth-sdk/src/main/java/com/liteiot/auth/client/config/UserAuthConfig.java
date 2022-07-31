package com.liteiot.auth.client.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.http.HttpServletRequest;

/**
 * 客户鉴权配置
 */
@Data
public class UserAuthConfig {

    /**
     * token header
     */
    @Value("${auth.user.token-header}")
    private String tokenHeader;

    /**
     * 公钥
     */
    private byte[] pubKeyByte;


    public String getToken(HttpServletRequest request){
        return request.getHeader(this.getTokenHeader());
    }
}
