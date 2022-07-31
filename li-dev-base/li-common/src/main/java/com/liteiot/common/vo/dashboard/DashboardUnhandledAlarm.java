package com.liteiot.common.vo.dashboard;

import lombok.Data;

import java.util.Date;

/**
 * Class:  DashboardAlarm
 * <p>
 * Author: zhaoyg
 * Date:   2022/4/13 16:10
 * Desc:   DashboardAlarm
 */
@Data
public class DashboardUnhandledAlarm {


    /**
     * 设备名称
     */
    private String deviceName;

    /**
     * 设备类型
     */
    private Integer deviceType;

    /**
     * 报警时间
     */
    private Date alarmTime;

    /**
     * 报警现象
     */
    private String alarmDetail;

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
}
