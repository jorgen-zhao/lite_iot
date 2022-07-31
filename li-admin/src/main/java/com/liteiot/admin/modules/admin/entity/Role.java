package com.liteiot.admin.modules.admin.entity;

import com.liteiot.common.validate.InsertVerify;
import com.liteiot.common.validate.UpdateVerify;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Table(name = "base_role")
@Data
public class Role {
    @Id
    private Integer id;

    @NotBlank(message = "角色编码不能为空", groups = {InsertVerify.class, UpdateVerify.class})
    private String code;

    private String name;

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