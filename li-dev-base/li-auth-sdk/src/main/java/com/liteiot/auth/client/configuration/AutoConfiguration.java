package com.liteiot.auth.client.configuration;

import com.liteiot.auth.client.config.ServiceAuthConfig;
import com.liteiot.auth.client.config.UserAuthConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * C
 */
@Configuration
@ComponentScan({"com.liteiot.auth"})
public class AutoConfiguration {
    @Bean
    ServiceAuthConfig getServiceAuthConfig(){
        return new ServiceAuthConfig();
    }

    @Bean
    UserAuthConfig getUserAuthConfig(){
        return new UserAuthConfig();
    }

}
