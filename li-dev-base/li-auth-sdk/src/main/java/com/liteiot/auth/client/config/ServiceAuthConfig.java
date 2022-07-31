package com.liteiot.auth.client.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

/**
 * 服务鉴权配置
 */
@Data
public class ServiceAuthConfig {

    /**
     * 公钥bytes
     */
    private byte[] pubKeyByte;

    /**
     * 客户Id
     */
    @Value("${auth.client.id:null}")
    private String clientId;

    /**
     * 客户密钥
     */
    @Value("${auth.client.secret}")
    private String clientSecret;

    /**
     * 系统名称
     */
    @Value("${spring.application.name}")
    private String applicationName;

    public String getClientId() {
        return "null".equals(clientId) ? applicationName : clientId;
    }
}
