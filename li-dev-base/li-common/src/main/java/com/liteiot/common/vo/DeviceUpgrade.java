package com.liteiot.common.vo;

import lombok.Data;

/**
 * Class:  DeviceUpgrade
 * <p>
 * Author: zhaoyg
 * Date:   2021/11/18 9:51
 * Desc:   设备升级
 */

@Data
public class DeviceUpgrade {

    /**
     * 组织架构
     */
    private int groupId;

    /**
     * 批次
     */
    private String batch;

    /**
     * 设备号
     */
    private String imei;

    /**
     * 设备名称
     */
    private String deviceName;

    /**
     * 当前软件版本
     */
    private int currentVersion;

    /**
     * 电量
     */
    private int batteryLevel;

    /**
     * 设备状态
     * 是否报警
     */
    private boolean deviceStatus;
}
