package com.liteiot.hub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"com.liteiot"})
@EnableFeignClients({"com.liteiot.hub.gate.rpc"})
public class BmsHubApplication {

    public static void main(String[] args) {
        SpringApplication.run(BmsHubApplication.class, args);
    }

}
