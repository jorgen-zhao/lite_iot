package com.liteiot.admin.modules.admin.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * 报警抓拍表
 *
 * @version 2022-04-21 17:26:22
 */
@Data
@Table(name = "alarm_capture")
public class AlarmCapture implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    @Id
    private Integer id;

    /**
     * 报警id
     */
    @Column(name = "alarm_id")
    private Integer alarmId;

    /**
     * 设备号
     */
    @Column(name = "imei")
    private String imei;

    /**
     * 图片地址
     */
    @Column(name = "pic_url")
    private String picUrl;

    /**
     * 创建时间
     */
    @Column(name = "crt_time")
    private Date crtTime;


}
