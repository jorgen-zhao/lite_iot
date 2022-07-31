package com.liteiot.common.vo;

import lombok.Data;

/**
 * Class:  VersionCheck
 * <p>
 * Author: zhaoyg
 * Date:   2021/11/16 10:11
 * Desc:   版本检查
 */

@Data
public class VersionCheck {

    /**
     * 设备名称
     */
    private String deviceName;

    /**
     * 设备号
     */
    private String imei;

    /**
     * 当前软件版本
     */
    private int currentVersion;

    /**
     * 服务器最新版本
     */
    private int latestVersion;

    /**
     * 硬件版本
     */
    private int hardVersion;

    /**
     * 升级包Id
     */
    private int upgraderId;

    /**
     * 升级包路径
     */
    private String url;
}
