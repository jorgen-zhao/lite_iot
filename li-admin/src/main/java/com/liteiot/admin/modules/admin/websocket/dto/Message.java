package com.liteiot.admin.modules.admin.websocket.dto;


import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import lombok.Data;

import java.net.InetSocketAddress;

@Data
public class Message {

    private int command;

    private ByteBuf data;

    private Channel remote;

    private InetSocketAddress sender;

}
