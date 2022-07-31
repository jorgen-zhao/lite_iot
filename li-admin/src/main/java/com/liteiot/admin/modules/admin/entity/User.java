package com.liteiot.admin.modules.admin.entity;

import com.liteiot.common.constant.RegexpConstant;
import com.liteiot.common.validate.InsertVerify;
import com.liteiot.common.validate.UpdateVerify;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Table(name = "base_user")
@Data
public class User {
    @Id
    @GeneratedValue(generator = "JDBC")
    @NotNull(message = "id不能为空", groups = {UpdateVerify.class})
    private Integer id;

    @Size(min = 2, max = 20, message = "账号长度应在2-20个字符", groups = {InsertVerify.class, UpdateVerify.class})
    @NotBlank(message = "账号不能为空", groups = {InsertVerify.class, UpdateVerify.class})
    private String username;

    @Size(min = 5, max = 20, message = "密码长度应在5-20个字符", groups = {InsertVerify.class})
    @NotBlank(message = "密码不能为空", groups = {InsertVerify.class})
    private String password;

    @Size(min = 2, max = 10, message = "姓名长度应在2-10个字符", groups = {InsertVerify.class, UpdateVerify.class})
    @NotBlank(message = "姓名不能为空", groups = {InsertVerify.class, UpdateVerify.class})
    private String name;

    /**
     * 状态
     */
    private String status;

    @Column(name = "group_id")
    @NotBlank(message = "groupId不能为空", groups = {UpdateVerify.class})
    private String groupId;

    @Column(name = "open_id")
    private String openId;

    @Column(name = "mobile_phone")
    @Pattern(regexp = RegexpConstant.PHONE_NOT_REQUIRED, message = "手机号格式错误", groups = {InsertVerify.class, UpdateVerify.class})
    private String mobilePhone;

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

    @Transient
    private List<Role> roleList;

    @Transient
    @NotBlank(message = "belongingGroupId不能为空", groups = {UpdateVerify.class})
    private String belongingGroupId;

    @Transient
    @NotBlank(message = "newBelongingGroupId不能为空", groups = {InsertVerify.class, UpdateVerify.class})
    private String newBelongingGroupId;

//    @Transient
//    private String belongingGroupName;

}