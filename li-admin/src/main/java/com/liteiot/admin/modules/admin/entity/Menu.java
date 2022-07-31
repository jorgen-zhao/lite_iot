package com.liteiot.admin.modules.admin.entity;

import com.liteiot.common.constant.AdminCommonConstant;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "base_menu")
@Data
public class Menu {
    @Id
    private Integer id;

    private String code;

    private String title;

    @Column(name = "parent_id")
    private Integer parentId = AdminCommonConstant.ROOT;

    private String href;

    private String icon;

    private String type;

    @Column(name = "order_num")
    private int orderNum;

    private String description;

    @Column(name = "crt_time")
    private Date crtTime;

    @Column(name = "crt_user")
    private String crtUser;

    @Column(name = "crt_name")
    private String crtName;

    @Column(name = "crt_host")
    private String crtHost;

    @Column(name = "upd_time")
    private Date updTime;

    @Column(name = "upd_user")
    private String updUser;

    @Column(name = "upd_name")
    private String updName;

    @Column(name = "upd_host")
    private String updHost;

    private String path;

    /**
     * 组件名称
     */
    private String component;

    /**
     * 组件路径
     */
    @Column(name = "component_path")
    private String componentPath;
    /**
     * "meta": {
     * "title": "系统设置",
     * "cache": true
     * }
     */
    private String meta;
}