package com.liteiot.admin.modules.admin.rpc;

import com.liteiot.admin.modules.admin.annotation.PushMsgCount;
import com.liteiot.admin.modules.admin.biz.PacketInfoBiz;
import com.liteiot.admin.modules.admin.biz.RecordDetailBiz;
import com.liteiot.api.vo.protocol.DeviceOperationReport;
import com.liteiot.api.vo.protocol.OTAReport;
import com.liteiot.api.vo.protocol.ReportPacketBody;
import com.liteiot.api.vo.timeout.OTATimeoutEvent;
import com.liteiot.common.constant.PacketConstants;
import com.liteiot.common.msg.ObjectRestResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

/**
 * 保存网关日志
 */
@RequestMapping("/api")
@RestController
@Slf4j
public class PacketRest {

    @Autowired
    private PacketInfoBiz packetInfoBiz;

    @Autowired
    private RecordDetailBiz recordDetailBiz;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;


    /**
     * 持久化来自设备上报的报文
     *
     * @param entity 报文解析对象
     * @return
     */
    @PushMsgCount
    @RequestMapping(value = "/packetInfo/persistPacket", method = RequestMethod.POST)
    public ObjectRestResponse<?> persistPacket(@RequestBody ReportPacketBody entity) {
        log.info("admin: 接收到hub调用, 传输数据: {}", entity);
        packetInfoBiz.persistPacket(entity);
        return new ObjectRestResponse<>();
    }

    /**
     * 持久化设备OTA流程下报文
     *
     * @param entity
     * @return
     */
    @PushMsgCount
    @RequestMapping(value = "/packetInfo/persistOTAPacket", method = RequestMethod.POST)
    public ObjectRestResponse<?> persistOTAPacket(@RequestBody OTAReport entity) {
        log.info("admin: 接收到hub调用, 传输数据: {}", entity);
        recordDetailBiz.persistOTAPacket(entity);
        return new ObjectRestResponse<>();
    }

    /**
     * 持久化设备响应的报文
     *
     * @param entity
     * @return
     */
    @PushMsgCount
    @RequestMapping(value = "/packetInfo/persistRespPacket", method = RequestMethod.POST)
    public ObjectRestResponse<?> persistRespPacket(@RequestBody DeviceOperationReport entity) {
        log.info("admin: 接收到hub调用, 传输数据: {}", entity);
        packetInfoBiz.persistRespPacket(entity);
        return new ObjectRestResponse<>();
    }

    /**
     * OTA超时响应处理
     *
     * @param imei 设备号
     * @return
     */
    @RequestMapping(value = "/packetInfo/otaRespTimeoutCheckFromHub", method = RequestMethod.GET)
    public ObjectRestResponse<?> otaRespTimeoutCheckFromHub(@RequestParam("imei") String imei) {
        log.info("admin: 接收到hub调用，OTA响应超时监测: {}", imei);
        applicationEventPublisher.publishEvent(new OTATimeoutEvent(this, PacketConstants.CPOTA, 10, imei));
        return new ObjectRestResponse<>();
    }

    /**
     * 远程调用: 持久化下发指令报文
     *
     * @param packetMsg
     * @return
     */
    @RequestMapping(value = "/packetInfo/persistSendPacketFromHub", method = RequestMethod.POST)
    public ObjectRestResponse<?> persistSendPacketFromHub(@RequestParam String imei, @RequestParam String packetMsg) {
        log.info("admin: 接收到hub调用, {}下发指令: {}", imei, packetMsg);
        packetInfoBiz.persistSendPacketFromHub(imei, packetMsg);
        return new ObjectRestResponse<>();
    }

}
