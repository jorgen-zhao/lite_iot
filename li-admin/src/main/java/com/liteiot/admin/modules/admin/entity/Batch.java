package com.liteiot.admin.modules.admin.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;


/**
 * 批次号管理表
 *
 * @version 2021-11-19 11:44:47
 */
@Data
@Table(name = "batch")
public class Batch implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    @Id
    private Integer id;

    /**
     * 批次号（bd+硬件版本+产品型号+生产日期）
     */
    @Column(name = "batch_code")
    private String batchCode;

    /**
     * 硬件版本
     */
    @Column(name = "hard_version")
    private Integer hardVersion;

    /**
     * 产品型号
     */
    @Column(name = "product_model")
    private String productModel;

    /**
     * 生产日期
     */
    @Column(name = "production_date")
    private String productionDate;

    /**
     * 批次号描述
     */
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

    public void setBatchCode() {
        this.batchCode = "BD".concat(this.productModel).concat(String.format("%02x", this.hardVersion)).concat(this.productionDate);
    }


}
