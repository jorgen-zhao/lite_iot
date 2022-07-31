package com.liteiot.admin.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.nio.channels.Channel;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Class:  SimpleLocalCache
 * <p>
 * Author: zhaoyg
 * Date:   2022/4/1 11:34
 * Desc:   SimpleLocalCache
 */

@Component
public class SimpleLocalCache {


    @Bean("simpleMapCache")
    public ConcurrentHashMap channelMap() {
        return new ConcurrentHashMap<String, List<Channel>>();
    }
}
