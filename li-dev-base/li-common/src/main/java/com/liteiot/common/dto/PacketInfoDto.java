package com.liteiot.common.dto;

import lombok.Data;

import java.util.List;

@Data
public class PacketInfoDto extends QueryCommonDto {

    /**
     * 设备号
     */
    private String imei;

    /**
     * 版本号
     */
    private Integer protocolVersion;

    private Integer offset;

}
