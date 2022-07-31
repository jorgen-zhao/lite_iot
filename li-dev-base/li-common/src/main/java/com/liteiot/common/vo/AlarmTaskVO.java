package com.liteiot.common.vo;

import lombok.Data;

import java.util.Date;

/**
 * Class:  AlarmTaskVO
 * <p>
 * Author: zhaoyg
 * Date:   2022/5/19 10:43
 * Desc:   AlarmTaskVO
 */

@Data
public class AlarmTaskVO {


    private Integer id;

    /**
     * 同一个报警任务标识Id
     */
    private Long taskMarkId;

    /**
     * 报警描述
     */
    private String remark;

    /**
     * imei
     */
    private String imei;

    /**
     * 设备名称
     */
    private String deviceName;

    /**
     * 设备类型
     */
    private Integer deviceType;

    /**
     * 批次号
     */
    private String batch;

    /**
     * 所属组织
     */
    private Integer groupId;

    private Date handleTime;
}
