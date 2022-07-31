package com.liteiot.api.vo.mq;

/**
 * Class:  PacketType
 * <p>
 * Author: zhaoyg
 * Date:   2022/4/28 19:27
 * Desc:   PacketType
 */
public enum PacketType {

    /**
     * 正常上报
     */
    REPORT_PACKET,

    /**
     * 命令响应
     */
    COMMAND_RESP,

    /**
     * IoT请求
     */
    IOT_REQUEST;
}
