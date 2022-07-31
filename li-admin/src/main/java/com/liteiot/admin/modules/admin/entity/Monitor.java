package com.liteiot.admin.modules.admin.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * 监控设备表
 *
 * @version 2022-04-20 15:34:21
 */
@Data
@Table(name = "monitor")
public class Monitor implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    @Id
    private Integer id;

    /**
     * 监控设备名称
     */
    @Column(name = "monitor_name")
    private String monitorName;

    /**
     * 所属组织
     */
    @Column(name = "group_id")
    private Integer groupId;

    /**
     * 设备序列号
     */
    @Column(name = "device_serial")
    private String deviceSerial;

    /**
     * 设备验证码
     */
    @Column(name = "validate_code")
    private String validateCode;

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
