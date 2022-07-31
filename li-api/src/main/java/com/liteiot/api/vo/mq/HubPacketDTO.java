package com.liteiot.api.vo.mq;

import lombok.Data;

/**
 * Class:  HubPacketDTO
 * <p>
 * Author: zhaoyg
 * Date:   2022/4/28 19:26
 * Desc:   HubPacketDTO
 */

@Data
public class HubPacketDTO {

    /**
     * 消息Id
     */
    private String msgId;

    /**
     * 消息类型
     */
    private PacketType packetType;

    /**
     * 消息内容
     */
    private String data;

    private String imei;
}
