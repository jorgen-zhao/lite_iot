package com.liteiot.proxy.packetZone;

import com.liteiot.api.vo.mq.HubPacketDTO;
import com.liteiot.api.vo.mq.PacketType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class PacketHandlerManager {

    /**
     * 处理类容器
     */
    private Map<PacketType, PacketHandler> map = new HashMap<>();

    /**
     * 处理类注册
     *
     * @param type     报警类型
     * @param computer 处理类
     */
    public void register(PacketType type, PacketHandler computer) {
        map.put(type, computer);
    }


    /**
     * 处理指令
     *
     * @param dto
     */
    public void handlePacket(HubPacketDTO dto) {
        // 报文处理
        PacketHandler builder = map.get(dto.getPacketType());
        if (builder != null) {
            builder.handlePacket(dto);
        }
    }
}
