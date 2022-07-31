package com.liteiot.common.bmsenum;

/**
 * Class:  MsgStatusEnum
 * <p>
 * Author: zhaoyg
 * Date:   2022/5/17 9:59
 * Desc:   MsgStatusEnum
 */
public interface MsgStatus {

    /**
     * 停止传递
     */
    int STOP_TRANSMIT = -1;

    /**
     * 继续传递
     */
    int CONTINUE_TRANSMIT = 1;
}
