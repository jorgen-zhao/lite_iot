package com.liteiot.admin.modules.admin.entity;

import com.liteiot.common.bmsenum.PageCustomizationEnum;
import com.liteiot.common.validate.InsertVerify;
import com.liteiot.common.validate.SelectVerify;
import com.liteiot.common.validate.UpdateVerify;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


/**
 * 页面定制化表
 * @version 2022-05-12 16:41:05
 */
@Data
@Table(name = "page_customization")
public class PageCustomization implements Serializable{
private static final long serialVersionUID=1L;

    @NotNull(message = "id不能为空",groups = {UpdateVerify.class})
    @Id
    private Integer id;

    /**
     * 所属组织
     */
    @NotNull(message = "groupId不能为空",groups = {InsertVerify.class, SelectVerify.class})
    @Column(name = "group_id")
    private Integer groupId;

    /**
     * 客户访问路径
     */
    @NotEmpty(message = "uniquePath不能为空",groups = {InsertVerify.class})
    @Column(name = "unique_path")
    private String uniquePath;

    /**
     * 类型
     *  {@link PageCustomizationEnum}
     */
    @NotNull(message = "type不能为空",groups = {InsertVerify.class})
    @Column(name = "type")
    private Integer type;

    /**
     * json文件的属性名称
     */
    @Column(name = "field_name")
    private String fieldName;

    /**
     * json文件的属性值
     */
    @Column(name = "field_value")
    private String fieldValue;
}
