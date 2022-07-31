package com.liteiot.admin.modules.admin.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.validation.constraints.Size;

import com.liteiot.common.validate.InsertVerify;
import com.liteiot.common.validate.UpdateVerify;


/**
 * 设备指令参数表
 *
 * @version 2022-06-15 14:48:38
 */
@Data
@Table(name = "device_command_param")
public class DeviceCommandParam implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    @Id
    private Integer id;


    /**
     * 指令id
     */
    @Column(name = "command_id")
    private Integer commandId;

    /**
     * 参数序号
     */
    @Column(name = "param_seq")
    private Integer paramSeq;


    /**
     * 参数名称
     */
    @Size(max = 50, message = "paramName 长度不能超过 50", groups = {InsertVerify.class, UpdateVerify.class})
    private String paramName;


    /**
     * 参数类型（1- 数值类；2- 字符类；3- 枚举类）
     */
    @Column(name = "param_type")
    private Integer paramType;


    /**
     * 默认值
     */
    @Size(max = 512, message = "defaultValue 长度不能超过 512", groups = {InsertVerify.class, UpdateVerify.class})
    private String defaultValue;


    /**
     * 最小值
     */
    @Size(max = 512, message = "minValue 长度不能超过 512", groups = {InsertVerify.class, UpdateVerify.class})
    private String minValue;


    /**
     * 最大值
     */
    @Size(max = 512, message = "maxValue 长度不能超过 512", groups = {InsertVerify.class, UpdateVerify.class})
    private String maxValue;


    /**
     * 枚举值(举例：1-开机,2-关机,3-报警)
     */
    @Size(max = 512, message = "optionsValue 长度不能超过 512", groups = {InsertVerify.class, UpdateVerify.class})
    private String optionsValue;


    /**
     * 参数单位
     */
    @Size(max = 512, message = "paramUnit 长度不能超过 512", groups = {InsertVerify.class, UpdateVerify.class})
    private String paramUnit;


    /**
     * 备注
     */
    @Size(max = 512, message = "remark 长度不能超过 512", groups = {InsertVerify.class, UpdateVerify.class})
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
