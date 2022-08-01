package com.liteiot.hub.gate.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.liteiot.api.vo.mq.HubPacketDTO;
import com.liteiot.api.vo.protocol.OTAReport;
import com.liteiot.hub.gate.rpc.ServiceAdminFeign;
import com.liteiot.hub.gate.sendService.ITerminalSender;
import com.liteiot.hub.gate.util.PacketClarify;
import com.liteiot.hub.gate.util.PacketParseUtil;
import com.liteiot.common.constant.PacketConstants;
import com.liteiot.common.packet.HexDumpMsgFormat;
import com.liteiot.common.redis.DeviceCommandCache;
import com.liteiot.common.vo.command.DeviceCommandVO;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Class:  OriginalMsgHandler
 * <p>
 * Author: zhaoyg
 * Date:   2021/9/7 15:40
 * Desc:   OriginalMsgHandler
 */
@Slf4j
@Component
public class HubOriginalMsgHandler {

    @Autowired
    private ITerminalSender sender;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Autowired
    private ServiceAdminFeign serviceAdminFeign;

    @Autowired
    private DeviceCommandCache deviceCommandCache;

    @Autowired
    private KafkaTemplate kafkaTemplate;


    public void handleProxyOriginalMsg(ChannelHandlerContext ctx, ByteBuf msg) {
        // 每当调用该接口，都会触发一次该方法
        deviceCommandCache.countSystemReceiveMsg();
        String remoteAddress = ctx.channel().remoteAddress().toString();

        byte[] bytes = ByteBufUtil.getBytes(msg);
        String packet = new String(bytes);

        HubPacketDTO dto = PacketClarify.clarify(bytes);
        switch (dto.getPacketType().name()) {
            case PacketConstants.IOT_REQUEST:
                OTAReport otaReport = PacketParseUtil.parseOTAPacket(packet);
                executeUpgradeCommand(remoteAddress, otaReport);
                break;
            case PacketConstants.COMMAND_RESP:
                // 执行指令下发
                executeCommand(remoteAddress, dto.getImei());
                break;
            case PacketConstants.REPORT_PACKET:
                byte[] decBytes = HexDumpMsgFormat.desByte2hexBytes(bytes);
                packet= new String(decBytes);
                // 执行指令下发
                executeCommand(remoteAddress, dto.getImei());
                break;
            default:
                log.info("未知指令类型：{}", dto.getPacketType().name());
                break;
        }
        log.info("{}: remote address: {}, received packet: {}", dto.getImei(), remoteAddress, packet);
        // JSON to String
        String json = JSON.toJSONString(dto, SerializerFeature.WriteMapNullValue);
        // 推送数据到kafka
        kafkaTemplate.send(dto.getPacketType().name(), json);

        // +ReqCOATP: 子包请求上行命令; +COPAPERR: 升级中错误 +COTAPOVER: 升级包接受完成 +BDG01RSP: 查询类报文上报
      /*  boolean isDeviceReportMsg = handleDeviceReportMsg(remoteAddress, packet);
        if (isDeviceReportMsg) return;

        // 接收到来自主站的报文
        String originPacket = new String(HexDumpMsgFormat.desByte2hexBytes(bytes));
        // 判断是否是多条报文
        // 说明： 此处是因为上报的报文存在多条, 但是不可用netty的处理方式, 因为上报的报文格式不统一。无法使用统一的方式处理，所以使用此种方式处理
        String[] packetDetails = originPacket.split(PacketConstants.SUFFIX);
        for (String originData : packetDetails) {
            originData = originData + PacketConstants.SUFFIX;
            log.info("remote address: {}, received originData: {}", remoteAddress, originData);
            // 添加报文过滤
            boolean isRegularPacket = PacketParseUtil.checkPacketRegular(originData);
            if (!isRegularPacket) {
                return;
            }

            // 截取报文 68 0A xxxxxxx
            String imei = originData.substring(4, 4 + 8 * 2);
            byte[] decBytes = HexDumpMsgFormat.formatStringToBytes(originData);
            ReportPacketBody packetBody = PacketParseUtil.parsePacket(imei, decBytes, originData);
            log.info("packetBody: {}", packetBody);

            // TODO 替换成web-client
            serviceAdminFeign.persistPacketFromHub(packetBody);
            // 如果存在指令，则下发指令
            executeCommand(remoteAddress, imei);
        }*/
        // 报文记录、远程调用, 存储入库
//        Mono<Void> mono = webClientBuilder.build()
//                .post()
//                .uri("http://li-admin/api/packetInfo/persistPacketFromHub")
//                .body(BodyInserters.fromValue(packetBody))
//                .retrieve()
//                .bodyToMono(Void.class);

        // 输出结果
//        log.debug(String.valueOf(mono.block()));
    }

    /**
     * 处理设备响应报文
     *
     * @param remoteAddress 远程地址
     * @param packet        报文
     * @return
     */
//    private boolean handleDeviceReportMsg(String remoteAddress, String packet) {
//        if (packet.startsWith("+")) {
//            String imei;
//            if (packet.startsWith(PacketConstants.DEVICE_RESP_HEAD)) {
//                DeviceOperationReport operationReport = PacketParseUtil.parseDeviceOperationReport(packet);
//                imei = operationReport.getImei();
//                serviceAdminFeign.persistRespPacketFromHub(operationReport);
//                // 执行指令下发
//                executeCommand(remoteAddress, imei);
//            } else {
//                OTAReport otaReport = PacketParseUtil.parseOTAPacket(packet);
//                imei = otaReport.getImei();
//                executeUpgradeCommand(remoteAddress, otaReport);
//                // 缓存OTA请求流程
//                serviceAdminFeign.persistOTAPacketFromHub(otaReport);
//            }
//            // 临时添加 当上报报文包含+时，缓存到Redis中，后期整个功能调整 最近接收响应只显示“+”号开头的数据
//            deviceCommandCache.putNewReportMsg(imei, packet);
//            log.info("received msg contain +, cache it to Redis. imei: {}, packet: {}", imei, packet);
//            return true;
//        }
//        return false;
//    }

    /**
     * 执行指令
     * 1. 下发最新指令
     * 2. 下发成功后，删除原有指令
     * 3. 缓存最新上报报文
     *
     * @param remoteAddress   远程地址
     * @param imei            设备号
     * @param originReportMsg 上报报文
     */
    private void executeCommand(String remoteAddress, String imei) {
        String latestCommand = deviceCommandCache.getLatestCommand(imei);
        if (StringUtils.isBlank(latestCommand)) {
            log.info("{} execute command. lastCommand is black. return", imei);
            return;
        }
        // 如果是升级指令, 则10秒后确认是否升级状态改变
        if (latestCommand.contains(PacketConstants.CPOTA)) {
            // 该操作不允许在不同服务中执行 applicationEventPublisher.publishEvent(new OTATimeoutEvent(this, PacketConstants.CPOTA, 10, imei));
            serviceAdminFeign.otaRespTimeoutCheckFromHub(imei);
        } else if (latestCommand.contains(PacketConstants.DEVICE_COMMAND_HEAD)) {
            serviceAdminFeign.persistSendPacketFromHub(imei, latestCommand);
        }
        String commandMsg = buildCommandMsg(latestCommand);
        try {
            sender.sendMsg(remoteAddress, commandMsg);
        } catch (Exception e) {
            throw new RuntimeException("下发指令异常");
        }
        log.info("{} execute command. lastCommand: {}, commandMsg: {}", imei, latestCommand, commandMsg);
        // 下发之后，删除该设备最新指令
        deviceCommandCache.delPreviousCommand(imei);
    }

    /**
     * 下发报文格式：+__real_command_\r\n --> e.g. +BDG01,10,SLPT,600,40\r\n
     *
     * @param command
     * @return
     */
    private String buildCommandMsg(String command) {
        return "+" + command + "\r\n";
    }

    /**
     * 执行文件升级
     *
     * @return
     * @Desc: +ReqCOTAP:0860527055265319,11,45,1024,45056,05
     */
    private void executeUpgradeCommand(String remoteAddress, OTAReport otaReport) {
        String head = otaReport.getHead();
        // 子包请求上行命令
//        final String OTAHEAD = "+ReqCOTAP";
        final String OTAHEAD = PacketConstants.REQCOTAP;
        if (!head.startsWith(OTAHEAD)) {
            return;
        }
        String imei = otaReport.getImei();
        log.info("执行文件升级: {}, 版本号: {}, 请求升级包下标: {}", imei, otaReport.getVersion(), otaReport.getCurrentNo());
        // 读取文件，下发指令
        byte[] bytes;
        try {
            bytes = readIndexingOfFile(imei, otaReport.getCurrentNo());
            sender.sendByte(remoteAddress, bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取指定索引（长度1024）字节流响应设备
     *
     * @param imei         设备
     * @param upgradeIndex 指定索引
     * @return
     * @throws IOException
     * @Desc 使用当前设备的设备号, 从Redis索引中获取当前设备的指令缓存, 从其中获取升级文件路径。然后使用FileInputStream 文件输入流读取指定长度[1KB]字节的数据;
     * @Update 1. 考虑读取文件指定长度参数化; 2. 读取文件方法优化
     */
    @SuppressWarnings("all")
    private byte[] readIndexingOfFile(String imei, int upgradeIndex) throws IOException {
        DeviceCommandVO deviceCommand = deviceCommandCache.getCachedDeviceCommand(imei);
        String pathname = deviceCommand.getOtaUrl();
        File file = new File(pathname);
        FileInputStream fis = new FileInputStream(file);
        // 剩余最后一包长度
        int restPacketSize =  (file.length() % 1024) == 0 ? 1024 : (int) (file.length() % 1024);
        // 执行次数
        long executeTimes = file.length() / 1024;
        executeTimes = (restPacketSize == 0 ? executeTimes : executeTimes + 1);

        byte[] buffer = new byte[1024];
        for (long index = 0; index < executeTimes; index++) {
            if (index == (executeTimes - 1)) {
                buffer = new byte[restPacketSize];
            }
            if (fis.read(buffer) != -1 && (index + 1) == upgradeIndex) {
                log.info("imei:{}, 请求升级包文件路径名称: {}, buffer: {}", imei, pathname, buffer);
                return buffer;
            }
        }
        log.info("imei:{}, 请求升级包文件路径名称: {}, buffer: {}", imei, pathname, buffer);
        return buffer;
    }
}
