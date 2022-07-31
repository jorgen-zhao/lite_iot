package com.liteiot.admin.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Class:  RestTemplateConfiguration
 * <p>
 * Author: zhaoyg
 * Date:   2021/12/29 14:52
 * Desc:   RestTemplateConfiguration
 */
@Component
public class RestTemplateConfiguration {

    @Bean("loadBalance")
    public RestTemplate loadBalance() {
        return new RestTemplate();
    }
}
