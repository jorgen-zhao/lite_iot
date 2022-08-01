package com.liteiot.lihub.gate.util;


import com.liteiot.api.vo.protocol.OTAReport;
import com.liteiot.common.constant.PacketConstants;
import lombok.extern.slf4j.Slf4j;

/**
 * Class:  PacketParseUtil
 * <p>
 * Author: zhaoyg
 * Date:   2021/9/9 9:45
 * Desc:   上报报文解析
 */
@Slf4j
public class PacketParseUtil {


    /**
     * 解析OTA请求报文
     * +ReqCOTAP: 子包请求上心命令
     * +COTAPERR:
     * 1. OTACMDERR: 升级命令校验错误
     * 2. PATCHERR: 子包错误
     * +COTAOVER: 子包接受完成通知
     *
     * @param originalPacket
     * @return
     */
    public static OTAReport parseOTAPacket(String originalPacket) {
        String[] reportMsgArray = originalPacket.split(",");
        String head = reportMsgArray[0];
        String imei = reportMsgArray[1];
        int index = -1;
        if (head.contains(":")) {
            String[] headArray = reportMsgArray[0].split(":");
            head = headArray[0];
            imei = headArray[1];
        }
        switch (head) {
            // 请求升级包
            case PacketConstants.REQCOTAP:
                String version = reportMsgArray[1];
                index = Integer.parseInt(reportMsgArray[2]);
                return new OTAReport(head, imei, version, index, originalPacket);
            // 子包错误
            case PacketConstants.COTAPERR:
                index = Integer.parseInt(reportMsgArray[2]);
                return new OTAReport(head, imei, index, originalPacket);
            // 请求包完成
            case PacketConstants.COTAPOVER:
                return new OTAReport(head, imei, -1, originalPacket);
            default:
                return new OTAReport(head, imei, originalPacket);
        }
    }

}
