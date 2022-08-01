package com.liteiot.gate;

import com.liteiot.auth.client.EnableCoreAuthClient;
import com.liteiot.gate.utils.DBLog;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringCloudApplication
@EnableCoreAuthClient
//TODO 切换为webclient
@EnableFeignClients({"com.liteiot.auth.client.feign"})
public class GatewayServerBootstrap {
    public static void main(String[] args) {
        DBLog.getInstance().start();
        SpringApplication.run(GatewayServerBootstrap.class, args);
    }
}
