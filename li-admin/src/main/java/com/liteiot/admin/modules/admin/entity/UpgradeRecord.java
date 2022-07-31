package com.liteiot.admin.modules.admin.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * 升级记录表
 *
 * @version 2021-11-15 14:02:50
 */
@Data
@Table(name = "upgrade_record")
public class UpgradeRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    // （0-待升级、1-升级中、2-下载完成、3-升级成功、4-未知、5-升级失败）
    public static final int WAIT_UPGRADING = 0;

    public static final int UPGRADING = 1;

    public static final int DOWNLOAD_COMPLETE = 2;

    public static final int UPGRADE_SUCCESS = 3;

    public static final int UNKNOWN = 4;

    public static final int UPGRADE_FAILED = 5;


    //主键
    @Id
    private Integer id;

    /**
     * 升级任务Id
     */
    @Column(name = "task_id")
    private Integer taskId;

    /**
     * 设备号
     */
    @Column(name = "imei")
    private String imei;

    /**
     * 升级前版本
     */
    @Column(name = "previous_version")
    private String previousVersion;

    /**
     * 升级后版本
     */
    @Column(name = "current_version")
    private String currentVersion;

    /**
     * 升级状态（0-待升级、1-升级中、2-下载完成、3-升级成功、4-未知、5-升级失败）
     */
    @Column(name = "upgrade_status")
    private int upgradeStatus;

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
