package com.liteiot.common.dto;

import lombok.Data;

@Data
public class DeviceInfoDto extends QueryCommonDto {
    /**
     * 设备号
     */
    private String imei;
}
