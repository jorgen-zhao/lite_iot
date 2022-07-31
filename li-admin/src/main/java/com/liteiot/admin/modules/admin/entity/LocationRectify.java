package com.liteiot.admin.modules.admin.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


/**
 * 定位矫正表
 *
 * @version 2022-04-19 20:22:34
 */
@Data
@Table(name = "location_rectify")
public class LocationRectify implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    @Id
    private Integer id;

    /**
     * 设备号
     */
    @Column(name = "imei")
    private String imei;

    /**
     * 维度
     */
    @Column(name = "latitude")
    private String latitude;

    /**
     * 经度
     */
    @Column(name = "longitude")
    private String longitude;

    /**
     * 是否不需要转换坐标
     */
    @Column(name = "real_coord")
    private Boolean realCoord;

    /**
     * 备注
     */
    @Column(name = "remark")
    private String remark;


}
