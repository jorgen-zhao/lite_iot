package com.liteiot.admin.modules.admin.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * 设备报警阈值配置表
 *
 * @version 2021-12-08 14:51:48
 */
@Data
@Table(name = "alarm_threshold")
public class AlarmThreshold implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    @Id
    private Integer id;

    /**
     * 设备号
     */
    @Column(name = "imei")
    private String imei;

    /**
     * 启用的配置项（fall, swing, spin, lean）
     */
    @Column(name = "enabled_config")
    private String enabledConfig;

    /**
     * 旋转报警阈值
     */
    @Column(name = "spin_threshold")
    private Double spinThreshold;

    /**
     * 倾斜报警阈值
     */
    @Column(name = "lean_threshold")
    private Double leanThreshold;

    /**
     * 位移报警阈值
     */
    @Column(name = "distance_threshold")
    private Double distanceThreshold;


    /**
     * 超时阈值配置
     */
    @Column(name = "timeout_threshold")
    private Double timeoutThreshold;

    /**
     * 频繁上报阈值配置
     */
    @Column(name = "frequency_threshold")
    private Double frequencyThreshold;

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
