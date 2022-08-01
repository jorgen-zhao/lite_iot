package com.liteiot.gate.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties("config")
@Getter
@Setter
public class PropertiesConfig {

    private List<String> corsUrl;
}
