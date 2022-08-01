package com.liteiot.proxy.listener;

import com.alibaba.fastjson.JSON;
import com.liteiot.api.vo.mq.HubPacketDTO;
import com.liteiot.proxy.packetZone.PacketHandlerManager;
import com.liteiot.common.constant.PacketConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Class:  PacketListener
 * <p>
 * Author: zhaoyg
 * Date:   2022/4/28 19:30
 * Desc:   PacketListener
 */

@Component
@Slf4j
public class PacketListener {

    @Autowired
    private PacketHandlerManager manager;

    /**
     * 消息监听
     *
     * @param consumerRecord
     */
    @KafkaListener(id = PacketConstants.KAFKA_CONSUMER_GROUP_ID, topics = {PacketConstants.REPORT_PACKET, PacketConstants.IOT_REQUEST, PacketConstants.COMMAND_RESP})
    public void listen(ConsumerRecord<?, String> consumerRecord) {

        Optional<String> kafkaMessage = Optional.ofNullable(consumerRecord.value());
        kafkaMessage.ifPresent(json -> {
            HubPacketDTO dto = JSON.parseObject(json, HubPacketDTO.class);
            log.info("接收消息: {}", dto);
            manager.handlePacket(dto);
        });
    }
}
