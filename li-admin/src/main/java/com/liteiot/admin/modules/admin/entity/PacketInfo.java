package com.liteiot.admin.modules.admin.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;


/**
 * 报文记录表
 *
 * @version 2021-09-17 10:15:35
 */
@Data
@Table(name = "packet_info")
@ApiModel("报文记录表")
public class PacketInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    @Id
    private Integer id;

    /**
     * imei
     */
    @Column(name = "imei")
    @ApiModelProperty("imei")
    private String imei;

    /**
     * 版本信息
     */
    @Column(name = "protocol_version")
    @ApiModelProperty("版本信息")
    private Integer protocolVersion;

    /**
     * 电池电量
     */
    @Column(name = "battery_level")
    @ApiModelProperty("电池电量")
    private Integer batteryLevel;

    /**
     * 温度
     */
    @Column(name = "temperature")
    @ApiModelProperty("温度")
    private Double temperature;

    /**
     * 字节的长度
     */
    @Column(name = "info_length")
    @ApiModelProperty("字节的长度")
    private Integer infoLength;

    /**
     * 无报警
     */
    @Column(name = "no_alarm")
    @ApiModelProperty("无报警")
    private boolean isNoAlarm;

    /**
     * 跌落报警
     */
    @Column(name = "drop_alarm")
    @ApiModelProperty("跌落报警")
    private boolean isDropAlarm;

    /**
     * 倾斜报警
     */
    @Column(name = "lean_alarm")
    @ApiModelProperty("倾斜报警")
    private boolean isLeanAlarm;

    /**
     * 斜率运动报警
     */
    @Column(name = "slope_move_alarm")
    @ApiModelProperty("斜率运动报警")
    private boolean isSlopeMoveAlarm;

    /**
     * 无可变信息
     */
    @Column(name = "no_var")
    @ApiModelProperty("无可变信息")
    private boolean isNoVar;

    /**
     * 有定位信息
     */
    @Column(name = "location_info")
    @ApiModelProperty("有定位信息")
    private boolean isLocationInfo;

    /**
     * 有位移信息
     */
    @Column(name = "distance_info")
    @ApiModelProperty("有定位信息")
    private boolean isDistanceInfo;

    /**
     * 有信号强度信息
     */
    @Column(name = "signal_info")
    @ApiModelProperty("有信号强度信息")
    private boolean isSignalInfo;

    /**
     * 有天线高程信息
     */
    @Column(name = "ant_ele_info")
    @ApiModelProperty("有天线高程信息")
    private boolean isAntEleInfo;

    /**
     * 有天线高程信息
     */
    @Column(name = "swing_info")
    @ApiModelProperty("是否存在摇摆数据")
    private boolean isSwingInfo;

    /**
     * Acc_Gd_Mag信息体的个数
     */
    @Column(name = "info_data_count")
    @ApiModelProperty("Acc_Gd_Mag信息体的个数")
    private boolean infoDataCount;

    /**
     * 通讯信息强度
     */
    @Column(name = "signal_value")
    @ApiModelProperty("通讯信息强度")
    private Integer signalValue;

    /**
     * 通讯信息强度
     */
    @Column(name = "antenna_elevation")
    @ApiModelProperty("通讯信息强度")
    private Integer antennaElevation;

    /**
     * 纬度
     */
    @Column(name = "latitude")
    @ApiModelProperty("纬度")
    private double latitude;

    /**
     * 经度
     */
    @Column(name = "longitude")
    @ApiModelProperty("经度")
    private double longitude;

    /**
     * 运营状态
     */
    @Column(name = "operate_status")
    @ApiModelProperty("运营状态")
    private boolean operateStatus;


    /**
     * 滚动角/倾斜角
     */
    @Column(name = "roll")
    @ApiModelProperty("滚动角/倾斜角")
    private Double roll;

    /**
     * 俯仰角
     */
    @Column(name = "pitch")
    @ApiModelProperty("俯仰角")
    private Double pitch;

    /**
     * 偏航角
     */
    @Column(name = "yaw")
    @ApiModelProperty("偏航角")
    private Double yaw;

    /**
     * 重力加速度数据ax
     */
    @Column(name = "ax")
    @ApiModelProperty("重力加速度数据x轴")
    private Integer ax;

    /**
     * 重力加速度数据ay
     */
    @Column(name = "ay")
    @ApiModelProperty("重力加速度数据y轴")
    private Integer ay;

    /**
     * 重力加速度数据az
     */
    @Column(name = "az")
    @ApiModelProperty("重力加速度数据z轴")
    private Integer az;

    /**
     * 陀螺仪数据gx
     */
    @Column(name = "gx")
    @ApiModelProperty("陀螺仪数据x轴")
    private Integer gx;

    /**
     * 陀螺仪数据gy
     */
    @Column(name = "gy")
    @ApiModelProperty("陀螺仪数据y轴")
    private Integer gy;

    /**
     * 陀螺仪数据gz
     */
    @Column(name = "gz")
    @ApiModelProperty("陀螺仪数据z轴")
    private Integer gz;

    /**
     * 磁力计数据mx
     */
    @Column(name = "mx")
    @ApiModelProperty("磁力计数据x轴")
    private Integer mx;

    /**
     * 磁力计数据my
     */
    @Column(name = "my")
    @ApiModelProperty("磁力计数据y轴")
    private Integer my;

    /**
     * 磁力计数据mz
     */
    @Column(name = "mz")
    @ApiModelProperty("磁力计数据z轴")
    private Integer mz;

    /**
     * 摇摆数据sx
     */
    @Column(name = "sx")
    @ApiModelProperty("摇摆数据sx")
    private Integer sx;

    /**
     * 摇摆数据sy
     */
    @Column(name = "sy")
    @ApiModelProperty("摇摆数据sy")
    private Integer sy;

    /**
     * 摇摆数据sz
     */
    @Column(name = "sz")
    @ApiModelProperty("摇摆数据sz")
    private Integer sz;

    /**
     * 位移数据dx
     */
    @Column(name = "dx")
    @ApiModelProperty("位移数据dx")
    private Integer dx;

    /**
     * 位移数据dy
     */
    @Column(name = "dy")
    @ApiModelProperty("位移数据dy")
    private Integer dy;

    /**
     * 位移数据dz
     */
    @Column(name = "dz")
    @ApiModelProperty("位移数据dz")
    private Integer dz;

    /**
     * 原始报文
     */
    @Column(name = "original_packet")
    @ApiModelProperty("原始报文")
    private String originalPacket;

    /**
     * 磁偏角
     */
    @Column(name = "heading_degrees")
    @ApiModelProperty("磁偏角")
    private Double headingDegrees;

    /**
     * 倾斜角
     */
    @Column(name = "lean_degrees")
    @ApiModelProperty("倾斜角")
    private Double leanDegrees;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 方向(单位为度数)
     */
    @Column(name = "degree")
    private Double degree;

    /**
     * 方位角
     */
    @Column(name = "azimuth")
    private String azimuth;

    /******************************* 冗余字段, 方便后期统计 *********************************/

    /**
     * 年 格式: yyyy
     */
    private String year;

    /**
     * 月 格式: yyyy-MM
     */
    private String month;

    /**
     * 日 格式: yyyy-MM-dd
     */
    private String day;

    /**
     * 时间 格式: yyyy-MM-dd HH:mm
     */
    private String time;
}
