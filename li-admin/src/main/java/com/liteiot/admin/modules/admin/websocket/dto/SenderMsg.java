package com.liteiot.admin.modules.admin.websocket.dto;


import lombok.Data;


@Data
public class SenderMsg {

    private int cmd;

    private byte[] msg;

    public SenderMsg(int cmd, byte[] msg) {
        this.cmd = cmd;
        this.msg = msg;
    }

    public int needSpace() {
        return msg.length + 8;
    }

    public int msgSpace() {
        return msg.length + 4;
    }
}