package com.liteiot.admin.modules.admin.redis.Impl;

import com.alibaba.fastjson.JSON;
import com.liteiot.admin.modules.admin.redis.CommonConfigCache;
import com.liteiot.common.constant.RedisKeyConstant;
import com.liteiot.common.vo.AlarmThresholdConfigVO;
import com.liteiot.common.vo.InitialAngle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Class:  CommonConfigCacheImpl
 * <p>
 * Author: zhaoyg
 * Date:   2021/9/22 11:25
 * Desc:   CommonConfigCacheImpl
 */
@Component
public class CommonConfigCacheImpl implements CommonConfigCache {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void cacheInitialAngle(InitialAngle initialAngle) {
        String key = String.format(RedisKeyConstant.REDIS_DEVICE_DEFAULT_ANGLE, initialAngle.getImei());
        Map map = JSON.parseObject(JSON.toJSONString(initialAngle), Map.class);
        stringRedisTemplate.opsForHash().putAll(key, map);
    }

    @Override
    public InitialAngle getCachedInitialAngle(String imei) {
        String key = String.format(RedisKeyConstant.REDIS_DEVICE_DEFAULT_ANGLE, imei);
        Map<Object, Object> map = stringRedisTemplate.opsForHash().entries(key);
        InitialAngle config = JSON.parseObject(JSON.toJSONString(map), InitialAngle.class);
        return config;
    }

    @Override
    public void clearCachedInitialAngle(String imei) {
        String key = String.format(RedisKeyConstant.REDIS_DEVICE_DEFAULT_ANGLE, imei);
        stringRedisTemplate.delete(key);
    }

    @Override
    public void cacheAlarmThresholdConfig(AlarmThresholdConfigVO config) {
        String key = String.format(RedisKeyConstant.REDIS_ALARM_THRESHOLD, config.getImei());
        Map map = JSON.parseObject(JSON.toJSONString(config), Map.class);
        stringRedisTemplate.opsForHash().putAll(key, map);
    }

    @Override
    public AlarmThresholdConfigVO getCachedAlarmThresholdConfig(String imei) {
        String key = String.format(RedisKeyConstant.REDIS_ALARM_THRESHOLD, imei);
        Map<Object, Object> map = stringRedisTemplate.opsForHash().entries(key);
        AlarmThresholdConfigVO config = JSON.parseObject(JSON.toJSONString(map), AlarmThresholdConfigVO.class);
        return config;
    }

    @Override
    public void clearCachedAlarmThresholdConfig(String imei) {
        String key = String.format(RedisKeyConstant.REDIS_ALARM_THRESHOLD, imei);
        stringRedisTemplate.delete(key);
    }
}
