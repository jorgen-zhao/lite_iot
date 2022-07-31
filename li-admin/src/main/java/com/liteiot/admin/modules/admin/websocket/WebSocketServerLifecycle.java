package com.liteiot.admin.modules.admin.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

/**
 * appServer webSocket创建
 */
@Slf4j
public abstract class WebSocketServerLifecycle implements DisposableBean {


    private final ServerBootstrap serverBootstrap;
    private final EventLoopGroup bossGroup;
    private final EventLoopGroup workGroup;

    private final SocketAddress socketAddress;

    public WebSocketServerLifecycle(final String ip, final int listener) {
        log.info("webSocket Server 监听启动ip:port {}:{}", ip, listener);
        serverBootstrap = new ServerBootstrap();

        bossGroup = new NioEventLoopGroup();
        workGroup = new NioEventLoopGroup();

        socketAddress = new InetSocketAddress(ip, listener);
    }

    protected abstract ChannelHandler channelHandlers();


    @Override
    public void destroy() throws Exception {
        stop();
    }

    protected void shutDown() throws Exception {
        stop();
    }

    public void startUp() throws InterruptedException {
        serverBootstrap.group(bossGroup, workGroup).channel(NioServerSocketChannel.class)
                .childOption(ChannelOption.SO_KEEPALIVE, true).childOption(ChannelOption.TCP_NODELAY, true)
                .childHandler(channelHandlers()).bind(socketAddress).sync().channel().closeFuture().addListener(ChannelFutureListener.CLOSE);
        log.info("webSocket服务器启动");
    }

    private void stop() throws InterruptedException {
        log.info("websocket server生命周期即将结束..............");
        workGroup.shutdownGracefully();
        workGroup.terminationFuture().sync();
        bossGroup.shutdownGracefully();
        bossGroup.terminationFuture().sync();
        log.info("websocket server生命周期已完结..............");
    }
}
