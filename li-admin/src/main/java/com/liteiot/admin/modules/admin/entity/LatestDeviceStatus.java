package com.liteiot.admin.modules.admin.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * VIEW
 *
 * @version 2021-11-16 10:05:43
 * @Q: 为什么与packetInfo重复呢: 该视图为设备的最新状态，前期开发可以将字段全部放出来。后期为了追求性能时，可以取特定字段
 */
@Data
@Table(name = "latest_device_status")
public class LatestDeviceStatus implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    @Id
    private Integer id;

    /**
     * imei
     */
    @Column(name = "imei")
    private String imei;

    /**
     * 版本信息
     */
    @Column(name = "protocol_version")
    private Integer protocolVersion;

    /**
     * 电池电量
     */
    @Column(name = "battery_level")
    private Integer batteryLevel;

    /**
     * 温度
     */
    @Column(name = "temperature")
    private Integer temperature;

    /**
     * 字节的长度
     */
    @Column(name = "info_length")
    private Integer infoLength;

    /**
     * 无报警
     */
    @Column(name = "no_alarm")
    private Boolean noAlarm;

    /**
     * 跌落报警
     */
    @Column(name = "drop_alarm")
    private Boolean dropAlarm;

    /**
     * 倾斜报警
     */
    @Column(name = "lean_alarm")
    private Boolean leanAlarm;

    /**
     * 斜率运动报警
     */
    @Column(name = "slope_move_alarm")
    private Boolean slopeMoveAlarm;

    /**
     * 无可变信息
     */
    @Column(name = "no_var")
    private Boolean noVar;

    /**
     * 有定位信息
     */
    @Column(name = "location_info")
    private Boolean locationInfo;

    /**
     * 有信号强度信息
     */
    @Column(name = "signal_info")
    private Boolean signalInfo;

    /**
     * 有天线高程信息
     */
    @Column(name = "ant_ele_info")
    private Boolean antEleInfo;

    /**
     * Acc_Gd_Mag信息体的个数
     */
    @Column(name = "info_data_count")
    private Boolean infoDataCount;

    /**
     * 通讯信息强度
     */
    @Column(name = "signal_value")
    private Integer signalValue;

    /**
     * 通讯信息强度
     */
    @Column(name = "antenna_elevation")
    private Integer antennaElevation;

    /**
     * 纬度
     */
    @Column(name = "latitude")
    private Double latitude;

    /**
     * 经度
     */
    @Column(name = "longitude")
    private Double longitude;

    /**
     * 滚动角/倾斜角
     */
    @Column(name = "roll")
    private Double roll;

    /**
     * 俯仰角
     */
    @Column(name = "pitch")
    private Double pitch;

    /**
     * 偏航角
     */
    @Column(name = "yaw")
    private Double yaw;

    /**
     * 重力加速度数据ax
     */
    @Column(name = "ax")
    private Integer ax;

    /**
     * 重力加速度数据ay
     */
    @Column(name = "ay")
    private Integer ay;

    /**
     * 重力加速度数据az
     */
    @Column(name = "az")
    private Integer az;

    /**
     * 陀螺仪数据gx
     */
    @Column(name = "gx")
    private Integer gx;

    /**
     * 陀螺仪数据gy
     */
    @Column(name = "gy")
    private Integer gy;

    /**
     * 陀螺仪数据gz
     */
    @Column(name = "gz")
    private Integer gz;

    /**
     * 磁力计数据mx
     */
    @Column(name = "mx")
    private Integer mx;

    /**
     * 磁力计数据my
     */
    @Column(name = "my")
    private Integer my;

    /**
     * 磁力计数据mz
     */
    @Column(name = "mz")
    private Integer mz;

    /**
     * 原始报文
     */
    @Column(name = "original_packet")
    private String originalPacket;

    /**
     * 磁偏角
     */
    @Column(name = "heading_degrees")
    private Double headingDegrees;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 冗余字段（年：yyyy）
     */
    @Column(name = "year")
    private String year;

    /**
     * 冗余字段（月：yyyy-MM）
     */
    @Column(name = "month")
    private String month;

    /**
     * 冗余字段（日：yyyy-MM-dd）
     */
    @Column(name = "day")
    private String day;

    /**
     * 冗余字段（时间：yyyy-MM-dd hh:mm）
     */
    @Column(name = "time")
    private String time;


}
