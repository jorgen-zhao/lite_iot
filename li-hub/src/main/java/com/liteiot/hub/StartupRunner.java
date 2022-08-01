package com.liteiot.hub;

import com.liteiot.hub.gate.server.HubTcpServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Class:  StartupRunner
 * <p>
 * Author: zhaoyg
 * Date:   2021/9/7 15:43
 * Desc:   StartupRunner
 */
@Component
public class StartupRunner implements CommandLineRunner {

    @Autowired
    private HubTcpServer server;

    @Override
    public void run(String... args) {

        try {
            server.startAsync();
            server.awaitRunning();
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                if (server.isRunning()) {
                    server.stopAsync();
                    server.awaitTerminated();
                }
            }));
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
