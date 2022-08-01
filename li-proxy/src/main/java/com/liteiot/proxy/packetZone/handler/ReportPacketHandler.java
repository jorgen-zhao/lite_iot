package com.liteiot.proxy.packetZone.handler;

import com.liteiot.api.vo.mq.HubPacketDTO;
import com.liteiot.api.vo.mq.PacketType;
import com.liteiot.api.vo.protocol.ReportPacketBody;
import com.liteiot.proxy.packetZone.AbstractPacketHandler;
import com.liteiot.proxy.util.PacketParseUtil;
import com.liteiot.common.constant.PacketConstants;
import com.liteiot.common.packet.HexDumpMsgFormat;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Class:  ReportPacketHandler
 * <p>
 * Author: zhaoyg
 * Date:   2022/4/28 19:54
 * Desc:   ReportPacketHandler
 */

@Component
@Slf4j
public class ReportPacketHandler extends AbstractPacketHandler {


    @Override
    protected PacketType getAlarmType() {
        return PacketType.REPORT_PACKET;
    }

    @Override
    protected void doHandle(HubPacketDTO dto) {
        String data = dto.getData();
        // 说明： 此处是因为上报的报文存在多条, 但是不可用netty的处理方式, 因为上报的报文格式不统一。无法使用统一的方式处理，所以使用此种方式处理
        String[] packetDetails = data.split(PacketConstants.SUFFIX);
        for (String originData : packetDetails) {
            originData = originData + PacketConstants.SUFFIX;
            // 添加报文过滤
            boolean isRegularPacket = PacketParseUtil.checkPacketRegular(originData);
            if (!isRegularPacket) {
                return;
            }

            String imei = dto.getImei();
            byte[] decBytes = HexDumpMsgFormat.formatStringToBytes(originData);
            ReportPacketBody packetBody = PacketParseUtil.parsePacket(imei, decBytes, originData);
            log.info("packetBody: {}", packetBody);
            // 远程调用保存报文
            adminFeign.persistPacket(packetBody);

        }

    }
}
