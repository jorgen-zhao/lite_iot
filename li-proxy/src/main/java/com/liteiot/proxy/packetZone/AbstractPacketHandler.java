package com.liteiot.proxy.packetZone;

import com.liteiot.api.vo.mq.HubPacketDTO;
import com.liteiot.api.vo.mq.PacketType;
import com.liteiot.proxy.feign.AdminFeign;
import com.liteiot.common.redis.DeviceCommandCache;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

public abstract class AbstractPacketHandler implements PacketHandler {


    @Autowired
    private PacketHandlerManager manager;

    @Autowired
    protected AdminFeign adminFeign;

    @Autowired
    protected DeviceCommandCache deviceCommandCache;

    /**
     * 获取命令类型
     *
     * @return
     */
    protected abstract PacketType getAlarmType();

    @PostConstruct
    protected void register() {
        manager.register(getAlarmType(), this);
    }


    @Override
    public void handlePacket(HubPacketDTO dto) {
        doHandle(dto);
    }

    protected abstract void doHandle(HubPacketDTO dto);

    // 共同调用feign接口

}
