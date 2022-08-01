package com.liteiot.lihub.gate.util;

import com.liteiot.api.vo.mq.HubPacketDTO;
import com.liteiot.api.vo.mq.PacketType;
import com.liteiot.common.constant.PacketConstants;
import com.liteiot.common.packet.HexDumpMsgFormat;
import com.liteiot.common.util.RandomStrGenerator;

/**
 * Class:  PacketClarify
 * <p>
 * Author: zhaoyg
 * Date:   2022/4/29 9:47
 * Desc:   PacketClarify
 */

public class PacketClarify {

    /**
     * 报文归类
     *
     * @param bytes
     * @return
     */
    public static HubPacketDTO clarify(byte[] bytes) {

        HubPacketDTO dto = new HubPacketDTO();
        String msgId = RandomStrGenerator.getRandomString(10);
        dto.setMsgId(msgId);
        String imei = null;

        String packet = new String(bytes);
        if (packet.startsWith("+")) {
            if (packet.startsWith(PacketConstants.DEVICE_RESP_HEAD)) {
                dto.setPacketType(PacketType.COMMAND_RESP);
                imei = packet.split(",")[1];
            } else {
                dto.setPacketType(PacketType.IOT_REQUEST);
            }
        } else {
            dto.setPacketType(PacketType.REPORT_PACKET);
            packet = new String(HexDumpMsgFormat.desByte2hexBytes(bytes));
            imei = packet.substring(4, 4 + 8 * 2);
        }
        dto.setImei(imei);
        dto.setData(packet);
        return dto;
    }

}
