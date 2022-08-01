package com.liteiot.hub.gate.server;

import com.liteiot.hub.gate.handler.HubOriginalMsgHandler;
import com.liteiot.hub.gate.sendService.ITerminalSender;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Class:  HubTcpServer
 * <p>
 * Author: zhaoyg
 * Date:   2021/9/7 15:29
 * Desc:   HubTcpServer
 */
@Slf4j
@Component
public class HubTcpServer extends AbstractOutTcpServer implements ITerminalSender {

    @Autowired
    private HubOriginalMsgHandler hubOriginalMsgHandler;

    public HubTcpServer(@Value("${server.tcp.server-listener.ip}") String ip,
                        @Value("${server.tcp.server-listener.port}") int listener,
                        @Value("${server.config.core}") int core) {
        super(ip, listener, core);
        log.info("ip: {}, port: {}, core: {}", ip, listener, core);
    }

    @Override
    protected ChannelHandler channelHandlers() {
        return new ServerChannelInitializer(this, hubOriginalMsgHandler);
    }

    @Override
    public void sendMsg(String remoteAddress, String msg) {
        if (StringUtils.isNotEmpty(msg)) {
            Channel channel = this.getChannelMapping().get(remoteAddress);
            ByteBuf sendBuf = Unpooled.wrappedBuffer(msg.getBytes());
            channel.writeAndFlush(sendBuf);
        }
    }

    @Override
    public void sendByte(String remoteAddress, byte[] bytes) {
        Channel channel = this.getChannelMapping().get(remoteAddress);
        ByteBuf buf = Unpooled.buffer();
        buf.writeBytes(bytes);
        channel.writeAndFlush(buf);
    }
}
