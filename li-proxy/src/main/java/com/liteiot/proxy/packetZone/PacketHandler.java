package com.liteiot.proxy.packetZone;

import com.liteiot.api.vo.mq.HubPacketDTO;

public interface PacketHandler {

    /**
     * 报文处理
     *
     * @param dto
     * @return
     */
    void handlePacket(HubPacketDTO dto);
}
