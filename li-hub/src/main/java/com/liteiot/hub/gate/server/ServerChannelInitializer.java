package com.liteiot.hub.gate.server;

import com.liteiot.hub.gate.handler.HubOriginalMsgHandler;
import com.liteiot.hub.gate.server.handler.HubInboundHandler;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import lombok.extern.slf4j.Slf4j;

/**
 * Class:  ServerChannelInitializer
 * <p>
 * Author: zhaoyg
 * Date:   2021/9/7 15:31
 * Desc:   ServerChannelInitializer
 */
@Slf4j
public class ServerChannelInitializer extends ChannelInitializer<Channel> {

    private HubTcpServer server;
    private HubOriginalMsgHandler hubOriginalMsgHandler;


    ServerChannelInitializer(HubTcpServer server, HubOriginalMsgHandler hubOriginalMsgHandler) {
        this.server = server;
        this.hubOriginalMsgHandler = hubOriginalMsgHandler;
    }

    @Override
    protected void initChannel(Channel ch) throws Exception {
        log.info("proxyServerChannel初始化");
        //保存连接的客户端channel
        ChannelPipeline pipeline = ch.pipeline();
        // 分隔符解码器不可用: 因为设备上报的报文格式不统一, 无法使用统一格式进行解析, 只能在业务方法进行处理
//        ByteBuf delimiter = Unpooled.copiedBuffer(new byte[]{(byte) 0x7E, (byte) 0x7E});
//        pipeline.addLast("framer", new DelimiterBasedFrameDecoder(256, delimiter));
        //第一个handler，注册连接
        pipeline.addLast(new HubInboundHandler(server, hubOriginalMsgHandler));
    }
}
