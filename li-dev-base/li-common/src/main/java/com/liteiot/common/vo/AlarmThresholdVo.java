package com.liteiot.common.vo;

import lombok.Data;

import java.util.List;

/**
 * Class:  AlarmThresholdVo
 * <p>
 * Author: zhaoyg
 * Date:   2022/3/8 16:14
 * Desc:   AlarmThresholdVo
 */
@Data
public class AlarmThresholdVo {

    /**
     * 统一执行设备列表
     */
    private List<String> imeis;

    /**
     * 允许的报警
     */
    private String enabledConfig;

    /**
     * 旋转报警阈值
     */
    private double spinThreshold;

    /**
     * 倾斜报警阈值
     */
    private double leanThreshold;

    /**
     * 位移报警阈值
     */
    private double distanceThreshold;

    /**
     * 超时阈值
     */
    private double timeoutThreshold;

    /**
     * 频繁上报阈值
     */
    private double frequencyThreshold;
}
