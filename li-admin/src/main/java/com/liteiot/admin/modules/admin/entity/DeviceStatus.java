package com.liteiot.admin.modules.admin.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * 设备状态表
 *
 * @version 2022-03-08 09:45:07
 */
@Data
@Table(name = "device_status")
public class DeviceStatus implements Serializable {
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
     * 是否频繁上报
     */
    @Column(name = "frequency_status")
    private Boolean frequencyStatus;

    /**
     * 频繁上报详情
     */
    @Column(name = "frequency_detail")
    private String frequencyDetail;

    /**
     * 频繁上报执行时间
     */
    @Column(name = "frequency_moment")
    private Date frequencyMoment;

    /**
     * 是否超时上报
     */
    @Column(name = "timeout_status")
    private Boolean timeoutStatus;

    /**
     * 超时上报详情
     */
    @Column(name = "timeout_detail")
    private String timeoutDetail;

    /**
     * 超时执行时间
     */
    @Column(name = "timeout_moment")
    private Date timeoutMoment;

    /**
     * frequency or timeout：冗余状态，不再进行判断后得出
     */
    @Column(name = "final_status")
    private String finalStatus;


}
