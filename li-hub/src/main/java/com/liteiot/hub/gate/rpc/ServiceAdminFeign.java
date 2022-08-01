package com.liteiot.hub.gate.rpc;

import com.liteiot.common.msg.ObjectRestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "li-admin")
public interface ServiceAdminFeign {


    /**
     * 远程调用: OTA响应报文超时
     *
     * @param imei
     * @return
     */
    @RequestMapping(value = "/api/packetInfo/otaRespTimeoutCheckFromHub", method = RequestMethod.GET)
    ObjectRestResponse<?> otaRespTimeoutCheckFromHub(@RequestParam String imei);

    /**
     * 远程调用: 持久化下发指令报文
     *
     * @param packetMsg
     * @return
     */
    @RequestMapping(value = "/api/packetInfo/persistSendPacketFromHub", method = RequestMethod.POST)
    ObjectRestResponse<?> persistSendPacketFromHub(@RequestParam String imei, @RequestParam String packetMsg);
}