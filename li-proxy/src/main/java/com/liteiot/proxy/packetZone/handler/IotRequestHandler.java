package com.liteiot.proxy.packetZone.handler;

import com.liteiot.api.vo.mq.HubPacketDTO;
import com.liteiot.api.vo.mq.PacketType;
import com.liteiot.api.vo.protocol.OTAReport;
import com.liteiot.proxy.packetZone.AbstractPacketHandler;
import com.liteiot.proxy.util.PacketParseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Class:  IotRequestHandler
 * <p>
 * Author: zhaoyg
 * Date:   2022/4/28 19:56
 * Desc:   IotRequestHandler
 */

@Component
@Slf4j
public class IotRequestHandler extends AbstractPacketHandler {

    @Override
    protected PacketType getAlarmType() {
        return PacketType.IOT_REQUEST;
    }

    @Override
    protected void doHandle(HubPacketDTO dto) {
        String data = dto.getData();
        OTAReport otaReport = PacketParseUtil.parseOTAPacket(data);
        // 缓存OTA请求流程
        adminFeign.persistOTAPacket(otaReport);
        deviceCommandCache.putNewReportMsg(otaReport.getImei(), data);
    }
}
