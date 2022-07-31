package com.liteiot.common.bmsenum;

/**
 * Class:  AlarmThreshold
 * <p>
 * Author: zhaoyg
 * Date:   2022/6/8 10:07
 * Desc:   AlarmThreshold
 */
public interface DefaultAlarmThreshold {

    /**
     * 默认启用报警阈值
     */
    String ENABLED_CONFIG = "fall,swing,distance,lean";

    /**
     * 默认倾斜报警阈值
     */
    double LEAN_THRESHOLD = 1.00;


    /**
     * 默认旋转报警阈值
     */
    double SPIN_THRESHOLD = 0.00;

    /**
     * 默认位移阈值
     */
    double DISTANCE_THRESHOLD = 10.00;


    /**
     * 默认频繁上报阈值
     */
    double FREQUENCY_THRESHOLD = 0.00;

    /**
     * 默认超时阈值
     */
    double TIMEOUT_THRESHOLD = 0.00;
}
