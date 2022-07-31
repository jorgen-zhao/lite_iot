package com.liteiot.admin.modules.admin.websocket.handler;

import com.liteiot.admin.modules.admin.websocket.dto.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.DecoderException;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.net.ssl.SSLException;

@AllArgsConstructor
@Slf4j
public class WebSocketInboundHandler extends SimpleChannelInboundHandler<Message> {

    private UserCheckinHandler userCheckinHandler;

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        log.info("客户端断开了webSocket，断开的对象:{}", ctx.channel());
        super.handlerRemoved(ctx);
        userCheckinHandler.removeConnectUser(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.info("webSocket连接异常:{}", cause.getMessage());
        if (cause instanceof SSLException || cause instanceof DecoderException) {
            log.error("SSLException received fatal alert:{}", cause.getMessage());
        } else {
            super.exceptionCaught(ctx, cause);
        }
        userCheckinHandler.removeConnectUser(ctx);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message msg) throws Exception {
        userCheckinHandler.cacheCheckedUser(ctx, msg);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof WebSocketServerProtocolHandler.HandshakeComplete) {
            ctx.pipeline().addLast(new ChannelOutboundHandlerAdapter() {
                @Override
                public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
                    if (msg instanceof ByteBuf) {
                        BinaryWebSocketFrame binaryWebSocketFrame = new BinaryWebSocketFrame((ByteBuf) msg);
                        ctx.write(binaryWebSocketFrame, promise);
                    } else {
                        super.write(ctx, msg, promise);
                    }
                }
            });
        }
        super.userEventTriggered(ctx, evt);
    }

}

