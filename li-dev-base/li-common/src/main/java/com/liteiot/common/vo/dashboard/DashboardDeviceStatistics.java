package com.liteiot.common.vo.dashboard;

import lombok.Data;

/**
 * Class:  DeviceStatistics
 * <p>
 * Author: zhaoyg
 * Date:   2022/4/13 16:06
 * Desc:   DeviceStatistics
 */

@Data
public class DashboardDeviceStatistics {

    /**
     * 所属组织
     */
    private String groupName;

    /**
     * 设备总数
     */
    private int devicesTotal;

    /**
     * 离线设备数
     */
    private int devicesOffline;

    /**
     * 经度
     */
    private double longitude;

    /**
     * 纬度
     */
    private double latitude;

    /**
     * 设备号
     */
    private String imei;

    private int getDeviceOnline() {
        return devicesTotal - devicesOffline;
    }
}
