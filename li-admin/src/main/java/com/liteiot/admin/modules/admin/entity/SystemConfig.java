package com.liteiot.admin.modules.admin.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * 系统配置表
 *
 * @version 2022-03-08 09:45:08
 */
@Data
@Table(name = "system_config")
public class SystemConfig implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    @Id
    private Integer id;

    /**
     * 组织Id
     */
    @Column(name = "group_id")
    private String groupId;

    /**
     * 定时执行周期时间
     */
    @Column(name = "schedule_time")
    private Integer scheduleTime;

    /**
     * 频繁上报的次数阈值
     */
    @Column(name = "frequency_threshold")
    private Integer frequencyThreshold;

    /**
     * 超时上报的时间阈值（unit：minutes）
     */
    @Column(name = "timeout_threshold")
    private Integer timeoutThreshold;

    /**
     * 备注
     */
    @Column(name = "remark")
    private String remark;

    /**
     * 创建时间
     */
    @Column(name = "crt_time")
    private Date crtTime;

    /**
     * 创建人
     */
    @Column(name = "crt_user")
    private String crtUser;

    /**
     * 创建人姓名
     */
    @Column(name = "crt_name")
    private String crtName;

    /**
     * 创建主机
     */
    @Column(name = "crt_host")
    private String crtHost;

    /**
     * 更新时间
     */
    @Column(name = "upd_time")
    private Date updTime;

    /**
     * 更新人
     */
    @Column(name = "upd_user")
    private String updUser;

    /**
     * 更新姓名
     */
    @Column(name = "upd_name")
    private String updName;

    /**
     * 更新主机
     */
    @Column(name = "upd_host")
    private String updHost;


}
