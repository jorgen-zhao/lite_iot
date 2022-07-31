package com.liteiot.admin.modules.auth.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "auth_client")
@Data
public class Client {
    @Id
    private Integer id;

    private String code;

    private String secret;

    private String name;

    private String locked = "0";

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
}