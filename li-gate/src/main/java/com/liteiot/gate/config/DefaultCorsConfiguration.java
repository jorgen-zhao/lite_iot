package com.liteiot.gate.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@Configuration
@Slf4j
public class DefaultCorsConfiguration {

    @Autowired
    private PropertiesConfig propertiesConfig;

    @Bean
    public CorsWebFilter corsWebFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration corsConfiguration = getDefaultConfig();
        log.info("应用配置的跨域配置：{}", corsConfiguration.getAllowedOrigins());
        //允许跨域的路径
        source.registerCorsConfiguration("/**", corsConfiguration);

        return new CorsWebFilter(source);
    }

    private CorsConfiguration getDefaultConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        //1.配置跨域
        //允许哪种请求头跨域
        corsConfiguration.addAllowedHeader("*");
        //允许哪种方法类型跨域 get post delete put
        corsConfiguration.addAllowedMethod("*");
        /*
         * corsConfiguration.addAllowedOrigin("*"); 会报错
         * allowedOrigins cannot contain the special value "*"since that cannot be set on the "Access-Control-Allow-Origin" response header.
         * To allow credentials to a set of origins, list them explicitly or consider using "allowedOriginPatterns" instead.
         * 解决方案：替换为corsConfiguration.addAllowedOriginPattern("*")
         */

        // 允许哪些请求源跨域
//        corsConfiguration.addAllowedOriginPattern("*");
        corsConfiguration.setAllowedOrigins(propertiesConfig.getCorsUrl());

        // 是否携带cookie跨域
        corsConfiguration.setAllowCredentials(true);
        return corsConfiguration;
    }



}
