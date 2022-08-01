package com.liteiot.proxy.util;


import com.liteiot.api.vo.protocol.CoordinateData;
import com.liteiot.api.vo.protocol.DeviceOperationReport;
import com.liteiot.api.vo.protocol.OTAReport;
import com.liteiot.api.vo.protocol.ReportPacketBody;
import com.liteiot.common.constant.PacketConstants;
import com.liteiot.common.packet.BytesUtil;
import com.liteiot.common.packet.HexDumpMsgFormat;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Class:  PacketParseUtil
 * <p>
 * Author: zhaoyg
 * Date:   2021/9/9 9:45
 * Desc:   上报报文解析
 */
@Slf4j
public class PacketParseUtil {


    /**
     * 判断报文是否合规
     *
     * @return
     */
    public static boolean checkPacketRegular(String originalPacket) {
        // 判断报文头是否为68开头
        if (StringUtils.isBlank(originalPacket)) {
            throw new RuntimeException("报文为空");
        }
        if (!originalPacket.startsWith(PacketConstants.PREFIX)) {
            log.info("报文不是以68开头，丢弃 originalPacket: {}", originalPacket);
            return false;
        }
        if (!originalPacket.endsWith(PacketConstants.SUFFIX)) {
            log.info("报文不是以7E7E结尾，丢弃 originalPacket: {}", originalPacket);
            return false;
        }

        // 异或校验
        int endIndex = originalPacket.lastIndexOf(PacketConstants.SUFFIX);
        String xorOriginalValue = originalPacket.substring(endIndex - 2, endIndex);
        // 从68开始到Acc_Gd_Mag对象信息体最后一个字节的BCC异或校验，校验码长度一个字节
        String needXORCheckStr = originalPacket.substring(0, endIndex - 2);

        String xorComputedValue = XORCheck(needXORCheckStr);
        if (!xorOriginalValue.equals(xorComputedValue)) {
            log.info("校验值未通过：xorComputedValue: {}, originalPacket: {}", xorComputedValue, originalPacket);
            return false;
        }
        return true;
    }

    // TODO 请求包异或校验添加

    /**
     * 异或校验
     */
    private static String XORCheck(String rawMsg) {
        // 16进制字符串需要转成10进制数组进行校验，然后再返回16进制字符串用于与原来的字符匹配
        byte[] bytes = HexDumpMsgFormat.hexStr2DesBytes(rawMsg);
        return BytesUtil.bytesXORCheck(bytes);
    }


    /**
     * 解析上报的报文
     *
     * @param imei  设备号
     * @param bytes 字节数组
     * @return
     */
    public static ReportPacketBody parsePacket(String imei, byte[] bytes, String originalPacket) {

        // 按位进行截取
        int version = bytes[1];
        int batteryLevel = bytes[10];
        double temperature = getTemperature(bytes[11], bytes[12]);
        int infoLength = getComputedValue(bytes[13], bytes[14]);
        boolean isNoAlarm = (bytes[15] == 0);
        boolean isDropAlarm = ((bytes[15] & 0b00000001) == 1);
        boolean isLeanAlarm = ((bytes[15] & 0b00000010) >> 1 == 1);
        boolean isSlopeMoveAlarm = ((bytes[15] & 0b00000100) >> 2 == 1);

        boolean isNoVar = (bytes[15] >>> 4 == 0);
        boolean isDistanceInfo = ((bytes[15] & 0b00001000) >> 3 == 1);
        boolean isLocationInfo = ((bytes[15] & 0b00010000) >> 4 == 1);
        boolean isSwingInfo = ((bytes[15] & 0b00100000) >> 5 == 1);
        boolean isSignalInfo = ((bytes[15] & 0b01000000) >> 6 == 1);
        boolean isAntEleInfo = ((bytes[15] & 0b10000000) >> 7 == 1);

        int signalValue = 0;
        int antennaElevation = 0;
        float latitude = 0f;
        float longtitude = 0f;
        // 偏移量（根据报文中的可变字段调整）
        int offset = 0;
        // 前面固定字节 从第17字节开始
        if (isSignalInfo) {
            // 信号1字节
            signalValue = bytes[17];
            offset += 1;
        }
        if (isAntEleInfo) {
            // 天线高程信息 2字节
            antennaElevation = getAntEleInfo(bytes[17 + offset], bytes[18 + offset]);
            offset += 2;
        }
        if (isLocationInfo) {
            // 经纬度 各4字节 共8字节
            byte[] lats = getOffsetBytes(bytes, offset, 4);
            latitude = getLocation(lats);
            offset += 4;

            byte[] longs = getOffsetBytes(bytes, offset, 4);
            longtitude = getLocation(longs);
            offset += 4;
        }
        // 重力加速度信息
        byte[] acc = getOffsetBytes(bytes, offset, 6);
        CoordinateData accelerator = getCoordinate(acc);
        offset += 6;
        // 陀螺仪数据
        byte[] gy = getOffsetBytes(bytes, offset, 6);
        CoordinateData gyroscope = getCoordinate(gy);
        offset += 6;
        // 磁力计数据
        byte[] mag = getOffsetBytes(bytes, offset, 6);
        CoordinateData magnet = getCoordinate(mag);
        offset += 6;
        // 处理状态
        boolean operateStatus = (bytes[17 + offset] & 0b00000001) == 1;
        offset += 1;
        // 摇摆信息 1字节
        CoordinateData swing = new CoordinateData();
        if (isSwingInfo) {
            swing.setX(bytes[17 + offset]);
            offset += 1;
            swing.setY(bytes[17 + offset]);
            offset += 1;
            swing.setZ(bytes[17 + offset]);
            offset += 1;
        }
        // 距离信息 2字节
        CoordinateData distance = new CoordinateData();
        if (isDistanceInfo) {
            byte[] distanceBytes = getOffsetBytes(bytes, offset, 6);
            distance = getCoordinate(distanceBytes);
            offset += 6;
        }
        // 拓展字段，先加offset，再加长度

        return new ReportPacketBody(version, imei, batteryLevel, temperature, infoLength, isNoAlarm, isDropAlarm, isLeanAlarm, isSlopeMoveAlarm, isNoVar, isDistanceInfo, isLocationInfo, isSwingInfo, isSignalInfo, isAntEleInfo,
                operateStatus, 1, signalValue, antennaElevation, latitude, longtitude, accelerator, gyroscope, magnet, swing, distance, originalPacket);
    }

    /**
     * 解析OTA请求报文
     * +ReqCOTAP: 子包请求上心命令
     * +COTAPERR:
     * 1. OTACMDERR: 升级命令校验错误
     * 2. PATCHERR: 子包错误
     * +COTAOVER: 子包接受完成通知
     *
     * @param originalPacket
     * @return
     */
    public static OTAReport parseOTAPacket(String originalPacket) {
        String[] reportMsgArray = originalPacket.split(",");
        String head = reportMsgArray[0];
        String imei = reportMsgArray[1];
        int index = -1;
        if (head.contains(":")) {
            String[] headArray = reportMsgArray[0].split(":");
            head = headArray[0];
            imei = headArray[1];
        }
        switch (head) {
            // 请求升级包
            case PacketConstants.REQCOTAP:
                String version = reportMsgArray[1];
                index = Integer.parseInt(reportMsgArray[2]);
                return new OTAReport(head, imei, version, index, originalPacket);
            // 子包错误
            case PacketConstants.COTAPERR:
                index = Integer.parseInt(reportMsgArray[2]);
                return new OTAReport(head, imei, index, originalPacket);
            // 请求包完成
            case PacketConstants.COTAPOVER:
                return new OTAReport(head, imei, -1, originalPacket);
            default:
                return new OTAReport(head, imei, originalPacket);
        }
    }

    /**
     * 北下：+BDG01,SorftwareVer,Cmd[,Para],BccNb\r\n
     * 南上：+BDG01RSP,IMEI,SorftwareVer,Cmd[,Para],BccNb\r\n
     *
     * @param originalPacket
     * @return
     */
    public static DeviceOperationReport parseDeviceOperationReport(String originalPacket) {
        String[] reportMsgArray = originalPacket.split(",");
        String head = reportMsgArray[0];
        String imei = reportMsgArray[1];
        Integer version = Integer.valueOf(reportMsgArray[2]);
        String cmd = reportMsgArray[3];
        String param = "";
        if (reportMsgArray.length == 6) {
            param = reportMsgArray[4];
        }
        return new DeviceOperationReport(head, imei, version, cmd, param, originalPacket);

    }


    /**
     * 截取指定长度的字节数组
     *
     * @param bytes
     * @param offset
     * @return
     */
    private static byte[] getOffsetBytes(byte[] bytes, int offset, int round) {
        byte[] fixedBytes = new byte[round];
        for (int i = 0; i < round; i++) {
            // 前面固定字节 从第17字节开始
            fixedBytes[i] = bytes[17 + i + offset];
        }
        return fixedBytes;
    }

    /**
     * 获取坐标轴信息
     *
     * @param bytes
     * @return
     * @Contain: 重力加速度、陀螺仪、磁力计
     */
    private static CoordinateData getCoordinate(byte[] bytes) {
        int x = getComputedValue(bytes[0], bytes[1]);
        int y = getComputedValue(bytes[2], bytes[3]);
        int z = getComputedValue(bytes[4], bytes[5]);
        return new CoordinateData(x, y, z);
    }

    // 将10进制的数组转换为16进制字符串, 再转换成10进制单精度
    private static float getLocation(byte[] bytes) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte aByte : bytes) {
            stringBuilder.append(String.format("%02X", aByte));
        }
        String hexString = stringBuilder.toString();
        return BytesUtil.hexToFloat(hexString);
    }

    /**
     * 获取天线高程信息
     *
     * @param highByte
     * @param lowByte
     * @return
     */
    private static int getAntEleInfo(byte highByte, byte lowByte) {
        return getDecInteger(highByte, lowByte);
    }

    /**
     * 获取温度
     *
     * @param highByte 高位
     * @param lowByte  低位
     * @return
     */
    private static double getTemperature(byte highByte, byte lowByte) {

        int computedValue = getComputedValue(highByte, lowByte);
        // 温度计算公式： （+-）Temper/256 +25℃
        double realTemperature = (computedValue / 256.0) + 25;
        return new BigDecimal(realTemperature).setScale(2, RoundingMode.HALF_UP).doubleValue();
//        return computedValue / 256 + 25;
    }

    /**
     * 根据两字节获取结果 高位在前
     *
     * @param highByte 高位
     * @param lowByte  低位
     * @return
     */
    private static int getComputedValue(byte highByte, byte lowByte) {
        Integer value = getDecInteger(highByte, lowByte);
        int computedValue;
        // 判断是否为补码
        boolean isComplement = (highByte & 0b10000000) == 0b10000000;
        if (isComplement) {
            int originTemp = 0xffff - value;
            int underZeroTemp = originTemp * (-1) - 1;
            computedValue = underZeroTemp;
        } else {
            computedValue = value;
        }
        return computedValue;
    }

    /**
     * 从16进制转为10进制
     *
     * @param highByte 高位
     * @param lowByte  地位
     * @return
     */
    private static Integer getDecInteger(byte highByte, byte lowByte) {
        String high = String.format("%02X", highByte);
        String low = String.format("%02X", lowByte);
        return Integer.valueOf(high + low, 16);
    }

    /**
     * 解析设备请求升级包
     *
     * @return
     */
    private static Integer parseUpgradeCommandRequest(String upgradeRequest) {
        String[] upgradeRequestArray = upgradeRequest.split(",");
        return Integer.valueOf(upgradeRequestArray[2]);
    }
}
