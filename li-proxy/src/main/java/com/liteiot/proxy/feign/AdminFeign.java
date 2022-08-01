package com.liteiot.proxy.feign;

import com.liteiot.api.vo.protocol.DeviceOperationReport;
import com.liteiot.api.vo.protocol.OTAReport;
import com.liteiot.api.vo.protocol.ReportPacketBody;
import com.liteiot.common.msg.ObjectRestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "li-admin")
public interface AdminFeign {

    /**
     * 远程调用: 持久化报文
     *
     * @param entity 上报报文数据
     * @return
     */
    @RequestMapping(value = "/api/packetInfo/persistPacket", method = RequestMethod.POST)
    ObjectRestResponse<?> persistPacket(@RequestBody ReportPacketBody entity);

    /**
     * 远程调用: 持久化OTA报文数据
     *
     * @param entity
     * @return
     */
    @RequestMapping(value = "/api/packetInfo/persistOTAPacket", method = RequestMethod.POST)
    ObjectRestResponse<?> persistOTAPacket(@RequestBody OTAReport entity);


    /**
     * 远程调用: 缓存设备响应报文
     *
     * @param entity
     * @return
     */
    @RequestMapping(value = "/api/packetInfo/persistRespPacket", method = RequestMethod.POST)
    ObjectRestResponse<?> persistRespPacket(@RequestBody DeviceOperationReport entity);
}