package com.liteiot.common.vo;

import lombok.Data;

/**
 * Class:  SimplePacket
 * <p>
 * Author: zhaoyg
 * Date:   2021/10/11 10:53
 * Desc:   配合接口 获取设备最新的状态(pageLatestVersion) / 获取设备最新坐标
 *
 * @see com.liteiot.admin.modules.admin.rest.PacketInfoController
 */

@Data
public class SimplePacket {

    /**
     * 设备号
     */
    private String imei;

    /**
     * 设备版本
     */
    private int protocolVersion;

    /**
     * 4个字节
     * 纬度
     */
    private double latitude;

    /**
     * 4个字节
     * 经度
     */
    private double longitude;

    /**
     * 是否不需要转换
     */
    private boolean realCoord;
}
