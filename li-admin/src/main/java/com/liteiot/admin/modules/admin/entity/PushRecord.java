package com.liteiot.admin.modules.admin.entity;

import com.liteiot.common.validate.InsertVerify;
import com.liteiot.common.validate.UpdateVerify;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;


/**
 * 推送记录
 * @version 2022-06-22 17:32:30
 */
@Data
@Table(name = "push_record")
public class PushRecord implements Serializable{
private static final long serialVersionUID=1L;

        //主键
    @Id
    @NotNull(message = "id 不能为空", groups = {UpdateVerify.class})
    private Integer id;

    
        /**
     * 接入系统id
     */
    @Column(name = "access_system_id")
    private Integer accessSystemId;

    /**
     * 接入系统名称
     */
    @Size(max = 50 ,message = "accessSystemName 长度不能超过 50",groups = {InsertVerify.class, UpdateVerify.class})
    @Transient
    private String accessSystemName;

    /**
     * 设备号
     */
    @Size(max = 50, message = "imei 长度不能超过 50", groups = {InsertVerify.class, UpdateVerify.class})
    @Column(name = "imei")
    private String imei;


    /**
     * 推送方式 {@link com.liteiot.common.bmsenum.PushMethod}
     */
    @Column(name = "push_method")
    private Integer pushMethod;
    
    
    /**
     * 推送内容
     */
    @Column(name = "push_content")
    private String pushContent;


    /**
     * 推送结果; {@link com.liteiot.common.bmsenum.OppositeStatus}
     */
    @Column(name = "push_result")
    private Integer pushResult;

    /**
     * 推送时间
     */
    @Column(name = "push_time")
    private Date pushTime;

    /**
     * 响应内容
     */
    @Column(name = "response_content")
    private String responseContent;
}
