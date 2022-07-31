package com.liteiot.common.bmsenum;

/**
 * Class:  HandleStatusEnum
 * <p>
 * Author: zhaoyg
 * Date:   2022/5/17 10:01
 * Desc:   HandleStatusEnum
 */
public interface HandleStatus {

    /**
     * 未处理
     */
    int UN_HANDLE = -1;

    /**
     * 已处理
     */
    int HANDLED = 1;

    /**
     * 终止
     */
    int TERMINAL = 99;
}
