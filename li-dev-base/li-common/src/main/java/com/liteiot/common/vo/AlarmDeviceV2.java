package com.liteiot.common.vo;

import lombok.Data;

import java.util.Date;

/**
 * Class:  AlarmDeviceV2
 * <p>
 * Author: zhaoyg
 * Date:   2022/5/19 15:46
 * Desc:   AlarmDeviceV2
 */
@Data
public class AlarmDeviceV2 {

    private Integer id;

    /**
     * imei
     */
    private String imei;

    /**
     * 设备名称
     */
    private String deviceName;

    /**
     * 设备类型
     */
    private Integer deviceType;

    /**
     * 批次号
     */
    private String batch;

    /**
     * 所属组织
     */
    private Integer groupId;

    /**
     * 地址
     */
    private String address;

    /**
     * 危险评分
     */
    private Integer dangerScore;


    /**
     * 报警描述
     */
    private String alarmDesc;


    private int alarmStatus;
    private int alarmResult;

    private Date crtTime;
    private Date updTime;

    private Integer serviceType;

}
