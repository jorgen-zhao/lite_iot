package com.liteiot.common.vo;

import lombok.Data;

/**
 * Class:  TaskStatus
 * <p>
 * Author: zhaoyg
 * Date:   2021/11/18 16:18
 * Desc:   TaskStatus
 */
@Data
public class TaskStatus {

    /**
     * 设备号
     */
    private String imei;

    /**
     * 设备名称
     */
    private String deviceName;

    /**
     * 所属组织
     */
    private int groupId;

    /**
     * 升级状态
     */
    private int upgradeStatus;
}
