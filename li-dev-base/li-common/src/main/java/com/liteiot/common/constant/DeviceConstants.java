package com.liteiot.common.constant;

import lombok.Data;

/**
 * Class:  DeviceConstants
 * <p>
 * Author: zhaoyg
 * Date:   2022/2/17 18:20
 * Desc:   DeviceConstants
 */

@Data
public class DeviceConstants {

    /* -------------- //  设备配置项    // -------------- */
    // 旋转配置项
    public static final int SPIN_CONFIG = 0;
    // 倾斜配置项
    public static final int LEAN_CONFIG = 1;
    // 旋转基准值
    public static final int SPIN_INIT_CONFIG = 2;
    // 倾斜基准值
    public static final int LEAN_INIT_CONFIG = 3;
    // 激活配置项
    public static final int ACTIVE_TYPE_CONFIG = 4;
    /* -------------- //  设备配置项    // -------------- */
    // 报警激活
    public static final int ALARM_ACTIVE = 1;
    // 命令激活
    public static final int COMMAND_ACTIVE = 2;
    // 实时上报演示激活
    public static final int REALTIME_DEMO_ACTIVE = 3;


}
