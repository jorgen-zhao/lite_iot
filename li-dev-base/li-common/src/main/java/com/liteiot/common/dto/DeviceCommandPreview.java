package com.liteiot.common.dto;

import lombok.Data;

/**
 * Class:  DeviceCommandPreview
 * <p>
 * Author: zhaoyg
 * Date:   2022/6/16 10:33
 * Desc:   DeviceCommandPreview
 */

@Data
public class DeviceCommandPreview {


    /**
     * 设备号
     */
    private String imei;

    /**
     * 指令编码
     */
    private String commandCode;

    /**
     * 指令参数
     */
    private String commandParam;
}
