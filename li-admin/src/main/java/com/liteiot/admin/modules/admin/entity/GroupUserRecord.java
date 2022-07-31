package com.liteiot.admin.modules.admin.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * 组织用户记录表
 *
 * @version 2021-12-28 13:38:08
 */
@Data
@Table(name = "group_user_record")
public class GroupUserRecord implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    @Id
    private Integer id;

    /**
     * 组织id
     */
    @Column(name = "group_id")
    private Integer groupId;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 状态：0 待审核 1 审核通过 2 审核不通过
     */
    @Column(name = "status")
    private int status;

    /**
     * 来源
     */
    @Column(name = "source")
    private String source;

    /**
     * 启用状态 0 不启用 1 启用
     */
    @Column(name = "enabled")
    private Boolean enabled;

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
     * 创建人姓名
     */
    @Column(name = "crt_name")
    private String crtName;

    /**
     * 更新时间
     */
    @Column(name = "upd_time")
    private Date updTime;

    /**
     * 更新姓名
     */
    @Column(name = "upd_name")
    private String updName;


}
