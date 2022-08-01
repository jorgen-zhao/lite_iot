package com.liteiot.proxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"com.liteiot"})
@EnableFeignClients({"com.liteiot.proxy.feign"})
public class BmsProxyApplication {

	public static void main(String[] args) {
		SpringApplication.run(BmsProxyApplication.class, args);
	}

}
