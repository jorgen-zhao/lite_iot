package com.liteiot.admin.modules.admin.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.liteiot.common.validate.InsertVerify;
import com.liteiot.common.validate.UpdateVerify;


/**
 * 推送数据选项表
 *
 * @version 2022-06-22 17:32:30
 */
@Data
@Table(name = "push_data_option")
public class PushDataOption implements Serializable {
    private static final long serialVersionUID = 1L;

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
     * 设备号
     */
    @Size(max = 50, message = "imei 长度不能超过 50", groups = {InsertVerify.class, UpdateVerify.class})
    @Column(name = "imei")
    private String imei;


    /**
     * 选择的属性名称
     */
    @Size(max = 500, message = "attributeValues 长度不能超过 500", groups = {InsertVerify.class, UpdateVerify.class})
    @Column(name = "attribute_values")
    private String attributeValues;


    /**
     * 创建时间
     */
    @Column(name = "crt_time")
    private Date crtTime;


}
