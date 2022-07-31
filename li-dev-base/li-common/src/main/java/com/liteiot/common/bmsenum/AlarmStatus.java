package com.liteiot.common.bmsenum;

/**
 * Class:  AlarmStatus
 * <p>
 * Author: zhaoyg
 * Date:   2022/5/26 13:57
 * Desc:   AlarmStatus
 */
public interface AlarmStatus {

    // 0-未处理、1-处理中、2-已处理
    int WAIT_HANDLE = 0;

    int HANDLING = 1;

    int HANDLED = 2;
}
