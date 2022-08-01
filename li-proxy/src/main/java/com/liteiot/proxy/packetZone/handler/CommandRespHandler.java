package com.liteiot.proxy.packetZone.handler;

import com.liteiot.api.vo.mq.HubPacketDTO;
import com.liteiot.api.vo.mq.PacketType;
import com.liteiot.api.vo.protocol.DeviceOperationReport;
import com.liteiot.proxy.packetZone.AbstractPacketHandler;
import com.liteiot.proxy.util.PacketParseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Class:  CommandRespHandler
 * <p>
 * Author: zhaoyg
 * Date:   2022/4/28 19:55
 * Desc:   CommandRespHandler
 */

@Component
@Slf4j
public class CommandRespHandler extends AbstractPacketHandler {
    @Override
    protected PacketType getAlarmType() {
        return PacketType.COMMAND_RESP;
    }

    @Override
    protected void doHandle(HubPacketDTO dto) {
        String data = dto.getData();
        DeviceOperationReport operationReport = PacketParseUtil.parseDeviceOperationReport(data);
        adminFeign.persistRespPacket(operationReport);
        deviceCommandCache.putNewReportMsg(operationReport.getImei(), data);
    }
}
