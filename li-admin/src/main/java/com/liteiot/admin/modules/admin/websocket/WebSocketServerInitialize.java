package com.liteiot.admin.modules.admin.websocket;


import com.liteiot.admin.modules.admin.websocket.handler.BinaryWebSocketDecoder;
import com.liteiot.admin.modules.admin.websocket.handler.TextWebSocketDecoder;
import com.liteiot.admin.modules.admin.websocket.handler.UserCheckinHandler;
import com.liteiot.admin.modules.admin.websocket.handler.WebSocketInboundHandler;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * websocketServer初始化pipeline
 */
@Slf4j
@Service
public class WebSocketServerInitialize extends WebSocketServerLifecycle {

    @Autowired
    private UserCheckinHandler userCheckinHandler;


    public WebSocketServerInitialize(@Value("${server.websocket.ip}") String ip,
                                     @Value("${server.websocket.port}") int port) {
        super(ip, port);
    }



    @Override
    protected ChannelHandler channelHandlers() {
        return new ChannelInitializer<Channel>() {
            @Override
            protected void initChannel(Channel ch) throws Exception {

                ChannelPipeline channelPipeline = ch.pipeline();

                channelPipeline.addLast(new HttpServerCodec());
                channelPipeline.addLast(new HttpObjectAggregator(65535));
                channelPipeline.addLast(new ChunkedWriteHandler());

                channelPipeline.addLast(new WebSocketServerProtocolHandler("/", "", true));
                channelPipeline.addLast(new TextWebSocketDecoder());
                channelPipeline.addLast(new BinaryWebSocketDecoder());
                channelPipeline.addLast(new WebSocketInboundHandler(userCheckinHandler));

            }
        };
    }
}
