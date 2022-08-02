package com.liteiot.api.vo.protocol;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class:  ReportPacketBody
 * <p>
 * Author: zhaoyg
 * Date:   2021/9/8 13:53
 * Desc:   上报报文协议体
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportPacketBody {


/**********************************************************************************************************************************************
 13 protoVx.y.z02 sfwVal SN08 MainInfolen01 BatLevel Temper02 Acc BCC 6p 6p
 **********************************************************************************************************************************/


    /**
     * 版本信息
     * 固定1个字节HEX，十位为发布版本号，个位为bug优化版本号
     */
    private int protocolVersion;

    /**
     * 通信模组ID
     * 8个字节的通信模组ID
     */
    private String sn;

    /**
     * 电池电量，范围0-100，固定1个字节
     */
    private int batteryLevel;

    /**
     * 温度，以2进制补码的形式上报，
     * 该值为0对应温度为25℃，0-15bit的最高位即第15位为符号位，
     * 温度计算公式： （+-）Temper/256 +25℃
     * 长度固定2个字节，高位在前。
     */
    private double temperature;

    /**
     * 从type开始，到Acc_Gd_Mag信息体最后一个字节的长度。固定2个字节，高位在前
     */
    private int infoLength;

    /**
     * 固定2字节
     * 低4位，报警信息:
     * 0000--无报警
     * 0001--跌落报警
     * 0010--倾斜报警
     * 0100--斜率运动报警
     * 1000--位移报警
     * 高4位，可变信息标志:
     * 0000--无可变信息
     * 0001--有定位信息
     * 0010--有摇摆信息
     * 0100--有信号强度信息
     * 1000--有天线高程信息
     */
    // 无报警
    private boolean isNoAlarm;
    // 跌落报警
    private boolean isDropAlarm;
    // 倾斜报警
    private boolean isLeanAlarm;
    //斜率报警
    private boolean isSlopeMoveAlarm;
    // 无可变信息
    private boolean isNoVar;
    // 位移信息
    private boolean isDistanceInfo;
    // 有定位信息
    private boolean isLocationInfo;
    // 有时间信息
//    private boolean isTimeInfo;
    // 有摇摆信息
    private boolean isSwingInfo;
    // 有信号强度信息
    private boolean isSignalInfo;
    // 有天线高程信息
    private boolean isAntEleInfo;

    /**
     * 固定1字节：运营状态
     */
    private boolean operateStatus;

    /**
     * 1字节
     * Acc_Gd_Mag信息体的个数
     */
    private int infoDataCount;

    /**
     * 固定1个字节
     * 通讯信息强度，消息中包不包含经纬度取决于type高4位；
     */
    private int signalValue;

    /**
     * 固定2个字节
     * 天线高程信息，消息中包不包含经纬度取决于type高4位；
     */
    private int antennaElevation;

    /**
     * 4个字节
     * 纬度
     */
    private double latitude;

    /**
     * 4个字节
     * 经度
     */
    private double longitude;


    /*********************************************************************************************************************
     3个轴固定6个字节，每2个字节对应一个轴，每个轴的数据为16bit
     最高位为符号位，当符号位为1时，以补码的形式发送。高位在前。
     ********************************************************************************************************************/
    /**
     * 重力加速度
     * Acc信息体是重力加速度数据，3个轴按顺序排列：ax,ay,az；
     */
    private CoordinateData accelerator;

    /**
     * 陀螺仪数据
     * 陀螺仪数据，3个轴按顺序排列：gx,gy,gz
     */
    private CoordinateData gyroscope;

    /**
     * 磁力计数据
     * 磁力计数据，3个轴按顺序排列：mx,my,mz。
     */
    private CoordinateData magnet;

    /**
     * 摇摆数据
     * 摇摆数据，3个轴按顺序排列：sx, xy, sz
     */
    private CoordinateData swing;

    /**
     * 位移数据
     * 位移数据, 3个轴按照顺序排列: dx, dy, dz
     */
    private CoordinateData distance;

    /**
     * 原始报文
     */
    private String originalPacket;
}
