package com.liteiot.admin.modules.admin.websocket.handler;

import com.liteiot.admin.modules.admin.websocket.dto.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class TextWebSocketDecoder extends MessageToMessageDecoder<TextWebSocketFrame> {


    @Override
    protected void decode(ChannelHandlerContext ctx, TextWebSocketFrame msg, List<Object> out) throws Exception {
        ByteBuf in = msg.content();

        if (in.readableBytes() < 5) {
            log.info("协议不符合要求关闭TCP连接.....");
            ctx.close();
        } else {
            Message message = new Message();
            byte[] bytes = new byte[5];
            in.readBytes(bytes);
            String _cmd_str = new String(bytes);
            message.setCommand(Integer.parseInt(_cmd_str));
            message.setData(in.retain());
            message.setRemote(ctx.channel());

            out.add(message);
        }

    }
}
