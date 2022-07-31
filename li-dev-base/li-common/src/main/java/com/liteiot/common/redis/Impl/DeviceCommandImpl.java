package com.liteiot.common.redis.Impl;

import com.alibaba.fastjson.JSON;
import com.liteiot.common.constant.RedisKeyConstant;
import com.liteiot.common.redis.DeviceCommandCache;
import com.liteiot.common.vo.command.DeviceCommandVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Class:  DeviceCommandImpl
 * <p>
 * Author: zhaoyg
 * Date:   2021/10/9 10:06
 * Desc:   DeviceCommandImpl
 */

@Component
@Slf4j
public class DeviceCommandImpl implements DeviceCommandCache {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void cacheDeviceCommand(DeviceCommandVO deviceCommandVO) {
        String key = String.format(RedisKeyConstant.REDIS_DEVICE_COMMAND, deviceCommandVO.getImei());
        Map map = JSON.parseObject(JSON.toJSONString(deviceCommandVO), Map.class);
        stringRedisTemplate.opsForHash().putAll(key, map);
    }

    @Override
    public void clearDeviceCommand(String imei) {
        String key = String.format(RedisKeyConstant.REDIS_DEVICE_COMMAND, imei);
        stringRedisTemplate.delete(key);
    }

    @Override
    public DeviceCommandVO getCachedDeviceCommand(String imei) {
        String key = String.format(RedisKeyConstant.REDIS_DEVICE_COMMAND, imei);
        Map<Object, Object> map = stringRedisTemplate.opsForHash().entries(key);
        return JSON.parseObject(JSON.toJSONString(map), DeviceCommandVO.class);
    }

    @Override
    public void delPreviousCommand(String imei) {
        String key = String.format(RedisKeyConstant.REDIS_DEVICE_COMMAND, imei);
        final String PREVIOUS_COMMAND = "previousCommand";
        stringRedisTemplate.opsForHash().put(key, PREVIOUS_COMMAND, "");
    }

    @Override
    public void putLatestCommand(String imei, String latestCommand) {
        String key = String.format(RedisKeyConstant.REDIS_DEVICE_COMMAND, imei);
        final String PREVIOUS_COMMAND = "previousCommand";
        stringRedisTemplate.opsForHash().put(key, PREVIOUS_COMMAND, latestCommand);
    }

    @Override
    public void putNewReportMsg(String imei, String reportMsg) {
        String key = String.format(RedisKeyConstant.REDIS_DEVICE_COMMAND, imei);
        final String PREVIOUS_REPORT_MSG = "previousReportMsg";
        stringRedisTemplate.opsForHash().put(key, PREVIOUS_REPORT_MSG, reportMsg);
    }

    @Override
    public String getLatestCommand(String imei) {
        String key = String.format(RedisKeyConstant.REDIS_DEVICE_COMMAND, imei);
        final String PREVIOUS_COMMAND = "previousCommand";
        return (String) stringRedisTemplate.opsForHash().get(key, PREVIOUS_COMMAND);
    }

    @Override
    public void countSystemReceiveMsg() {
        stringRedisTemplate.opsForValue().increment(RedisKeyConstant.SYSTEM_RECEIVE_MSG_COUNT, 1);
    }

    @Override
    public boolean cachedSystemReceiveMsgCount() {
        return Boolean.TRUE.equals(stringRedisTemplate.hasKey(RedisKeyConstant.SYSTEM_RECEIVE_MSG_COUNT));
    }

    @Override
    public void initSystemReceiveMsg(long count) {
        stringRedisTemplate.opsForValue().set(RedisKeyConstant.SYSTEM_RECEIVE_MSG_COUNT, String.valueOf(count));
    }

    @Override
    public long getSystemReceiveMsg() {
        return Long.parseLong(stringRedisTemplate.opsForValue().get(RedisKeyConstant.SYSTEM_RECEIVE_MSG_COUNT));
    }
}
