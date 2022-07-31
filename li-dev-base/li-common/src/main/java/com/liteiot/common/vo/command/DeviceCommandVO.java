package com.liteiot.common.vo.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 设备指令实体
 *
 * @Desc：可拓展，需要新加字段时，直接声明即可。前提需要将之间的数据清理/或者进行转换
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceCommandVO {

    /**
     * 设备号
     */
    private String imei;

    /**
     * 上一条指令
     */
    private String previousCommand;

    /**
     * 上一条上报报文
     */
    private String previousReportMsg;

    /**
     * ota Url
     */
    private String otaUrl;

    /**
     * 是否为OTA升级: 1-是 2-否
     */
    private String otaFlag;

    public DeviceCommandVO(String imei) {
        this.imei = imei;
        this.otaFlag = "2";
    }
}
