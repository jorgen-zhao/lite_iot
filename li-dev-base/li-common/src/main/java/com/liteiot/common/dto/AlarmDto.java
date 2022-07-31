package com.liteiot.common.dto;

import lombok.Data;

import java.util.List;

@Data
public class AlarmDto extends QueryCommonDto {

    /**
     * 设备名
     */
    private String deviceName;

    /**
     * 设备号
     */
    private String imei;

    /**
     * 设备类型
     */
    private Integer deviceType;

    /**
     * 处理状态
     */
    private Integer alarmStatus;

    /**
     * 设备号集合
     */
    private List<String> imeiList;

}
