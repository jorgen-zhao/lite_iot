package com.liteiot.admin.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 用户配置
 */
@Configuration
@Data
public class UserConfiguration {
    @Value("${jwt.token-header}")
    private String userTokenHeader;
}
