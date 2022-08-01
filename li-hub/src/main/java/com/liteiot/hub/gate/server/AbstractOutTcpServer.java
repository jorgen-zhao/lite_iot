package com.liteiot.hub.gate.server;

import com.google.common.util.concurrent.AbstractIdleService;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Class:  AbstractOutTcpServer
 * <p>
 * Author: zhaoyg
 * Date:   2021/9/7 15:23
 * Desc:   AbstractOutTcpServer
 */
@Slf4j
public abstract class AbstractOutTcpServer extends AbstractIdleService {

    private final ServerBootstrap serverBootstrap;
    private final EventLoopGroup bossGroup;
    private final EventLoopGroup workGroup;

    private final SocketAddress socketAddress;

    protected abstract ChannelHandler channelHandlers();

    @Getter
    private Map<String, Channel> channelMapping = new ConcurrentHashMap<>(16);

    public AbstractOutTcpServer(final String ip, final int listener, final int core) {
        log.info("AbstractOutTcpServer listen port:{}", listener);
        serverBootstrap = new ServerBootstrap();

        bossGroup = new NioEventLoopGroup(1);
        workGroup = new NioEventLoopGroup(core);

        socketAddress = new InetSocketAddress(ip, listener);
    }

    @Override
    protected void shutDown() throws Exception {
        stopServer();
    }

    @Override
    protected void startUp() throws InterruptedException {
        serverBootstrap.group(bossGroup, workGroup)
                .channel(NioServerSocketChannel.class)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childHandler(channelHandlers())
                .bind(socketAddress)
                .sync()
                .channel()
                .closeFuture().addListener(ChannelFutureListener.CLOSE);
    }

    private void stopServer() throws InterruptedException {
        log.info("shut down tcp server..............start");
        workGroup.shutdownGracefully();
        workGroup.terminationFuture().sync();
        bossGroup.shutdownGracefully();
        bossGroup.terminationFuture().sync();
        log.info("shut down tcp server..............end");
    }
}
