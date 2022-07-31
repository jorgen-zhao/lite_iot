package com.liteiot.admin.modules.admin.entity;

import com.liteiot.common.bmsenum.OppositeStatus;
import com.liteiot.common.constant.RegexpConstant;
import com.liteiot.common.validate.InsertVerify;
import com.liteiot.common.validate.UpdateVerify;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;


/**
 * 接入系统表
 *
 * @version 2022-06-22 17:32:30
 */
@Data
@Table(name = "access_system")
public class AccessSystem implements Serializable {

    private static final long serialVersionUID = 1L;

    //主键
    @Id
    @NotNull(message = "id 不能为空", groups = {UpdateVerify.class})
    private Integer id;


    /**
     * 接入系统名称
     */
    @Size(min = 2, max = 50, message = "接入系统名称长度应在2-50个字符", groups = {InsertVerify.class, UpdateVerify.class})
    @NotBlank(message = "接入系统名称不能为空", groups = {InsertVerify.class, UpdateVerify.class})
    @Column(name = "name")
    private String name;


    /**
     * 接入系统编码
     */
    @Column(name = "code")
    private String code;


    /**
     * 联系人姓名
     */
    @Size(min = 2, max = 5, message = "联系人姓名长度应在2-5个字符", groups = {InsertVerify.class, UpdateVerify.class})
    @NotBlank(message = "联系人姓名不能为空", groups = {InsertVerify.class, UpdateVerify.class})
    @Column(name = "contact_name")
    private String contactName;


    /**
     * 联系人电话
     */
    @Pattern(regexp = RegexpConstant.PHONE_REQUIRED, message = "联系人电话格式错误", groups = {InsertVerify.class, UpdateVerify.class})
    @Column(name = "contact_phone")
    private String contactPhone;


    /**
     * 是否启用; {@link OppositeStatus}
     */
    @Column(name = "enabled")
    private Integer enabled;

    /**
     * 截止时间
     */
    @Column(name = "deadline_date")
    @JsonFormat(locale = "zh", timezone = "Asia/Shanghai", pattern = "yyyy-MM-dd")
    private Date deadlineDate;


    /**
     * 推送url
     */
    @Size(max = 256, message = "url长度不能超过256个字符", groups = {InsertVerify.class, UpdateVerify.class})
    @Pattern(regexp = RegexpConstant.URL, message = "url格式错误", groups = {InsertVerify.class, UpdateVerify.class})
    @Column(name = "url")
    private String url;


    /**
     * appId
     */
    @Size(min = 2, max = 50, message = "appId长度应在2-50个字符", groups = {InsertVerify.class, UpdateVerify.class})
    @NotBlank(message = "appId不能为空", groups = {InsertVerify.class, UpdateVerify.class})
    @Column(name = "app_id")
    private String appId;


    /**
     * appKey
     */
    @Size(min = 2, max = 100, message = "appKey长度应在2-100个字符", groups = {InsertVerify.class, UpdateVerify.class})
    @NotBlank(message = "appKey不能为空", groups = {InsertVerify.class, UpdateVerify.class})
    @Column(name = "app_key")
    private String appKey;


    /**
     * 备注
     */
    @Size(max = 100, message = "备注长度不能超过100个字符", groups = {InsertVerify.class, UpdateVerify.class})
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
