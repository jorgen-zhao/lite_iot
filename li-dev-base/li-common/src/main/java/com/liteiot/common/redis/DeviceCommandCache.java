package com.liteiot.common.redis;

import com.liteiot.common.vo.command.DeviceCommandVO;

/**
 * Class:  DeviceCommandCache
 * <p>
 * Author: zhaoyg
 * Date:   2021/10/9 10:06
 * Desc:   DeviceCommandCache
 */
public interface DeviceCommandCache {

    /**
     * 缓存设备指令
     *
     * @param deviceCommandVO
     */
    void cacheDeviceCommand(DeviceCommandVO deviceCommandVO);

    /**
     * 清理设备指令
     * @param imei
     */
    void clearDeviceCommand(String imei);

    /**
     * 获取设备指令
     *
     * @param imei
     * @return
     */
    DeviceCommandVO getCachedDeviceCommand(String imei);

    /**
     * 删除上一条指令
     *
     * @param imei
     */
    void delPreviousCommand(String imei);

    /**
     * 更新最新上报报文
     *
     * @param imei
     * @param reportMsg
     */
    void putNewReportMsg(String imei, String reportMsg);

    /**
     * 更新最新指令
     *
     * @param imei
     * @param command
     */
    void putLatestCommand(String imei, String command);

    /**
     * 获取最新指令
     *
     * @param imei
     * @return
     */
    String getLatestCommand(String imei);

    /**
     * 计算系统接受设备消息数
     */
    void countSystemReceiveMsg();

    /**
     * 是否缓存过系统接受设备消息数
     *
     * @return
     */
    boolean cachedSystemReceiveMsgCount();

    /**
     * 初始化系统接受设备消息数
     */
    void initSystemReceiveMsg(long count);

    /**
     * 获取系统接受设备消息数
     *
     * @return
     */
    long getSystemReceiveMsg();
}
