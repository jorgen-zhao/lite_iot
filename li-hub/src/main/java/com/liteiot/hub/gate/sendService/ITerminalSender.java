package com.liteiot.hub.gate.sendService;

/**
 * Class:  IBeidouTerminalSender
 * <p>
 * Author: zhaoyg
 * Date:   2021/9/7 15:20
 * Desc:   tcp数据发送
 */
public interface ITerminalSender {
    /**
     * 发送数据
     *
     * @param msg 消息
     */
    void sendMsg(String remoteAddress, String msg);

    void sendByte(String remoteAddress, byte[] bytes);
}

