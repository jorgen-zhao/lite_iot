package com.liteiot.common.vo;

import lombok.Data;

/**
 * Class:  DeviceConfig
 * <p>
 * Author: zhaoyg
 * Date:   2022/2/17 16:45
 * Desc:   DeviceConfig
 */
@Data
public class DeviceConfig {

    private String imei;

    private Double initX;

    private Double initY;

    private Double initZ;
}
