package com.liteiot.admin.modules.admin.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * VIEW
 *
 * @version 2021-11-16 11:04:32
 * @Q: 为什么与软件版本管理重复呢: 该视图为同等硬件版本下软件的最大版本，前期开发可以将字段全部放出来。后期为了追求性能时，可以取特定字段
 */
@Data
@Table(name = "max_soft_version")
public class MaxSoftVersion implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    @Id
    private Integer id;

    /**
     * 发布日期
     */
    @Column(name = "publish_date")
    private String publishDate;

    /**
     * 软件版本
     */
    @Column(name = "soft_version")
    private int softVersion;

    /**
     * 硬件版本
     */
    @Column(name = "hard_version")
    private int hardVersion;

    /**
     * 产品型号
     */
    @Column(name = "product_modal")
    private String productModal;

    /**
     * 存放地址
     */
    @Column(name = "url")
    private String url;

    /**
     * 文件名称
     */
    @Column(name = "filename")
    private String filename;

    /**
     * 文件类型（0 - 通用类型 1-专属类型）
     */
    @Column(name = "file_type")
    private Boolean fileType;

    /**
     * 所属层级（默认当前登录用户所在层级；也可以选择其下子层级）
     */
    @Column(name = "group_id")
    private Integer groupId;

    /**
     * 升级包描述
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
