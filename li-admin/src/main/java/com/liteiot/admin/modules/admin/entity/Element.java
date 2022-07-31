package com.liteiot.admin.modules.admin.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "base_element")
@Data
public class Element {
    @Id
    private Integer id;

    private String code;

    private String type;

    private String name;

    private String uri;

    @Column(name = "menu_id")
    private String menuId;

    @Column(name = "parent_id")
    private String parentId;

    private String path;

    private String method;

    private String description;

    @Column(name = "crt_time")
    private Date crtTime;

    @Column(name = "crt_user")
    private String crtUser;

    @Column(name = "crt_name")
    private String crtName;

    @Column(name = "crt_host")
    private String crtHost;
}