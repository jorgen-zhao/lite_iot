package com.liteiot.admin.modules.admin.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;


/**
 * 平台版本表
 *
 * @version 2022-03-17 13:04:53
 */
@Data
@Table(name = "platform_version")
public class PlatformVersion implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    @Id
    private Integer id;

    /**
     * 类型（0-平台 1-小程序）
     */
    @Column(name = "type")
    private int type;

    /**
     * 主版本号
     */
    @Column(name = "major")
    private Integer major;

    /**
     * 次版本号
     */
    @Column(name = "minor")
    private Integer minor;

    /**
     * 修订号
     */
    @Column(name = "patch")
    private Integer patch;

    /**
     * 版本号x.x.x
     */
    @Column(name = "version")
    private String version;

    /**
     * 版本详情
     */
    @Column(name = "detail")
    private String detail;

    /**
     * 附件存放路径（url）
     */
    @Column(name = "attachment")
    private String attachment;

    /**
     * 发布时间
     */
    @Column(name = "publish_time")
    private Date publishTime;

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
