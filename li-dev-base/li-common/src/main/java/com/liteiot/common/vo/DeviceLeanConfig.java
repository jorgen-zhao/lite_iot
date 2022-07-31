package com.liteiot.common.vo;

import lombok.Data;

/**
 * Class:  DeviceLeanConfig
 * <p>
 * Author: zhaoyg
 * Date:   2022/2/17 15:13
 * Desc:   DeviceLeanConfig
 * 1 - 倾斜配置项
 */
@Data
public class DeviceLeanConfig extends DeviceConfig{

    private Double leanAx;

    private Double leanAy;

    private Double leanAz;

    private Double leanBx;

    private Double leanBy;

    private Double leanBz;

    private Double leanCx;

    private Double leanCy;

    private Double leanCz;

    private Double leanDx;

    private Double leanDy;

    private Double leanDz;
}
