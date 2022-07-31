package com.liteiot.common.vo;

import lombok.Data;

/**
 * Class:  DeviceExtraConfig
 * <p>
 * Author: zhaoyg
 * Date:   2022/2/17 14:52
 * Desc:   DeviceExtraConfig
 * 0 - 旋转配置项
 */
@Data
public class DeviceSpinConfig extends DeviceConfig {

    private Double spinAx;

    private Double spinAy;

    private Double spinAz;

    private Double SpinBx;

    private Double spinBy;

    private Double spinBz;

    private Double spinCx;

    private Double spinCy;

    private Double spinCz;

    private Double spinDx;

    private Double spinDy;

    private Double spinDz;
}
