package com.liteiot.admin.modules.admin.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * 升级任务表
 *
 * @version 2021-11-15 11:43:56
 */
@Data
@Table(name = "upgrade_task")
public class UpgradeTask implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 升级包Id
     */
    @Column(name = "upgrader_id")
    private Integer upgraderId;

    /**
     * 设备号（通过逗号隔开）
     */
    @Column(name = "imeis")
    private String imeis;

    /**
     * 升级方式
     * 0: 升级包优先
     * 1: 设备优先
     */
    @Column(name = "upgrade_type")
    private Integer upgradeType;

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
