package com.liteiot.common.vo;

import lombok.Data;

/**
 * Class:  DeviceThreshold
 * <p>
 * Author: zhaoyg
 * Date:   2021/12/8 14:58
 * Desc:   DeviceThreshold
 */

@Data
public class DeviceThreshold {


    /**
     * imei
     */
    private String imei;

    /**
     * 设备名称
     */
    private String deviceName;

    /**
     * 所属组织
     */
    private Integer groupId;

    /**
     * 启用的配置项（fall, swing, spin, lean）
     */
    private String enabledConfig;

    /**
     * 旋转报警阈值
     */
    private Double spinThreshold;

    /**
     * 倾斜报警阈值
     */
    private Double leanThreshold;

    /**
     * 位移报警阈值
     */
    private Double distanceThreshold;

    /**
     * 超时阈值
     */
    private Double timeoutThreshold;

    /**
     * 频繁上报阈值
     */
    private Double frequencyThreshold;
}
