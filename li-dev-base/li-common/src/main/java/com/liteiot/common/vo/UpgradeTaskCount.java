package com.liteiot.common.vo;

import lombok.Data;

import java.util.Date;

/**
 * Class:  UpgradeTaskCount
 * <p>
 * Author: zhaoyg
 * Date:   2021/11/18 14:28
 * Desc:   UpgradeTaskCount
 */
@Data
public class UpgradeTaskCount {

    /**
     * 任务Id
     */
    private int taskId;

    /**
     * 软件名称
     */
    private String filename;

    /**
     * 软件版本
     */
    private int softVersion;

    /**
     * 升级时间
     */
    private Date crtTime;

    /**
     * 等待升级数量
     */
    private int waitUpgrade;

    /**
     * 开始升级数量
     */
    private int upgrading;

    /**
     * 下载完成数量
     */
    private int downloadComplete;

    /**
     * 升级成功数量
     */
    private int upgradeSuccess;

    /**
     * 升级失败数量
     */
    private int upgradeFailure;

    /**
     * 未知状态数量
     */
    private int unknownCount;

    /**
     * 总升级数量
     */
    private int upgradeSum;
}
