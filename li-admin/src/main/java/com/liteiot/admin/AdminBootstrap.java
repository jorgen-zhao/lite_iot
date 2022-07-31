package com.liteiot.admin;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Swagger接口调整
 * 1. https://www.cnblogs.com/xsge/p/13996625.html
 * 2. https://blog.csdn.net/u022812849/article/details/117374768
 * 新版本访问地址：http://192.168.1.126:8762/swagger-ui/index.html#/
 * 旧版本访问地址：http://192.168.1.126:8762/swagger-ui.html#/
 */
@EnableDiscoveryClient
@EnableCircuitBreaker
@SpringBootApplication(scanBasePackages = {"com.liteiot"})
@EnableTransactionManagement
@MapperScan("com.liteiot.admin.modules.*.mapper")
@EnableSwagger2Doc
@EnableAsync
public class AdminBootstrap {
    public static void main(String[] args) {
        new SpringApplicationBuilder(AdminBootstrap.class).run(args);
    }
}
