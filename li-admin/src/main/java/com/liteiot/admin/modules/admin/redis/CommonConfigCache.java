package com.liteiot.admin.modules.admin.redis;

import com.liteiot.common.vo.AlarmThresholdConfigVO;
import com.liteiot.common.vo.InitialAngle;

/**
 * Class:  CommonConfigCache
 * <p>
 * Author: zhaoyg
 * Date:   2021/9/22 11:04
 * Desc:   通用配置缓存，便于快速获取配置项
 */
public interface CommonConfigCache {

    /**
     * 缓存初始角度
     *
     * @param initialAngle
     */
    void cacheInitialAngle(InitialAngle initialAngle);

    /**
     * 获取初始角度
     *
     * @param imei
     * @return
     */
    InitialAngle getCachedInitialAngle(String imei);

    /**
     * 清理初始角度
     *
     * @param imei
     */
    void clearCachedInitialAngle(String imei);

    /**
     * 缓存配置项
     *
     * @param config
     */
    void cacheAlarmThresholdConfig(AlarmThresholdConfigVO config);

    /**
     * 获取配置项
     *
     * @return
     */
    AlarmThresholdConfigVO getCachedAlarmThresholdConfig(String imei);

    /**
     * 删除配置项
     */
    void clearCachedAlarmThresholdConfig(String imei);

}
