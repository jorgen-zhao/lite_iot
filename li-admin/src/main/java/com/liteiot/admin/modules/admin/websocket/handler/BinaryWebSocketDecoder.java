package com.liteiot.admin.modules.admin.websocket.handler;

import com.liteiot.admin.modules.admin.websocket.dto.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class BinaryWebSocketDecoder extends MessageToMessageDecoder<BinaryWebSocketFrame> {


    @Override
    protected void decode(ChannelHandlerContext ctx, BinaryWebSocketFrame msg, List<Object> out) throws Exception {
        ByteBuf in = msg.content();

        if (in.readableBytes() >= 8) {
            int length = in.readInt();

            if (length > 65535 || in.readableBytes() < length) {
                log.error("处理消息BinaryWebSocket异常");
                ctx.close();
            } else {
                int cmd = in.readInt();
                Message message = new Message();
                message.setRemote(ctx.channel());
                message.setData(in.readRetainedSlice(length - 4));
                message.setCommand(cmd);

                out.add(message);
            }
        }
    }
}
