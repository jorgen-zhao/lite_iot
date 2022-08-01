package com.liteiot.lihub.gate.server.handler;

import com.liteiot.lihub.gate.handler.HubOriginalMsgHandler;
import com.liteiot.lihub.gate.server.HubTcpServer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * Class:  HubInboundHandler
 * <p>
 * Author: zhaoyg
 * Date:   2021/9/7 15:22
 * Desc:   HubInboundHandler
 */
@Slf4j
public class HubInboundHandler extends SimpleChannelInboundHandler<ByteBuf> {

    private HubTcpServer server;
    private HubOriginalMsgHandler hubOriginalMsgHandler;

    public HubInboundHandler(HubTcpServer server, HubOriginalMsgHandler hubOriginalMsgHandler) {
        this.server = server;
        this.hubOriginalMsgHandler = hubOriginalMsgHandler;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        String clientIdentify = ctx.channel().remoteAddress().toString();
        log.info("clientIdentify: {} active......", clientIdentify);
        server.getChannelMapping().put(clientIdentify, ctx.channel());
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        String clientIdentify = ctx.channel().remoteAddress().toString();
        server.getChannelMapping().remove(clientIdentify);
        super.channelInactive(ctx);
        log.info("clientIdentify: {} inactive......", clientIdentify);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        hubOriginalMsgHandler.handleProxyOriginalMsg(ctx, msg);
    }
}
