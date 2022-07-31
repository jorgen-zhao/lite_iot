package com.liteiot.common.vo;

import lombok.Data;

/**
 * Class:  SimpleDeviceInfo
 * <p>
 * Author: zhaoyg
 * Date:   2021/10/11 10:21
 * Desc:   配合接口 根据组织树，返回设备列表 getDevicesByGroup
 *
 * @see com.liteiot.admin.modules.admin.rest.DeviceInfoController
 */

@Data
public class SimpleDeviceInfo {

    /**
     * 设备号
     */
    private String imei;


    /**
     * 设备名称
     */
    private String deviceName;
}
