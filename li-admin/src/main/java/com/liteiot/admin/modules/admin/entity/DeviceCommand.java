package com.liteiot.admin.modules.admin.entity;

import com.liteiot.common.validate.InsertVerify;
import com.liteiot.common.validate.UpdateVerify;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;


/**
 * 设备指令表
 *
 * @version 2022-06-15 14:48:38
 */
@Data
@Table(name = "device_command")
public class DeviceCommand implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    @Id
    private Integer id;


    /**
     * 指令名称
     */
    @Size(max = 255, message = "commandName 长度不能超过 255", groups = {InsertVerify.class, UpdateVerify.class})
    private String commandName;


    /**
     * 指令编码
     */
    @Size(max = 50, message = "commandCode 长度不能超过 50", groups = {InsertVerify.class, UpdateVerify.class})
    private String commandCode;


    /**
     * 指令版本
     */
    @Column(name = "command_version")
    private Integer commandVersion;

    /**
     * 指令类型（1- 操作类；2-设置类；3-查询类）
     */
    @Column(name = "command_type")
    private Integer commandType;


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
