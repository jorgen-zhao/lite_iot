package com.liteiot.admin.modules.admin.entity;

import lombok.Data;

import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.liteiot.common.validate.InsertVerify;
import com.liteiot.common.validate.UpdateVerify;


/**
 * 推送数据全量表
 *
 * @version 2022-06-22 17:32:30
 */
@Data
@Table(name = "push_data_full")
public class PushDataFull implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    @Id
    @NotNull(message = "id 不能为空", groups = {UpdateVerify.class})
    private Integer id;


    /**
     * 属性中文名称
     */
    @Size(max = 50, message = "attributeName 长度不能超过 50", groups = {InsertVerify.class, UpdateVerify.class})
    @Column(name = "attribute_name")
    private String attributeName;


    /**
     * 属性英文名称
     */
    @Size(max = 50, message = "attributeValue 长度不能超过 50", groups = {InsertVerify.class, UpdateVerify.class})
    @Column(name = "attribute_value")
    private String attributeValue;


    /**
     * 属性类型 {@link com.liteiot.common.bmsenum.AttributeType}
     */
    @Column(name = "attribute_type")
    private Integer attributeType;


    /**
     * 属性类型名称
     */
    @Size(max = 50, message = "attributeTypeValue 长度不能超过 50", groups = {InsertVerify.class, UpdateVerify.class})
    @Column(name = "attribute_type_value")
    private String attributeTypeValue;


}
