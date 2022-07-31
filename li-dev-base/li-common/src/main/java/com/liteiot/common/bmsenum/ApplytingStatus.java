package com.liteiot.common.bmsenum;

/**
 * Class:  ApplytingStatus
 * <p>
 * Author: zhaoyg
 * Date:   2022/5/25 15:07
 * Desc:   ApplytingStatus
 */
public interface ApplytingStatus {


    // 0 待审核 1 审核通过 2 审核不通过
    int WAIT_AUDIT = 0;

    int AUDIT_SUCCESS = 1;

    int AUDIT_FAILURE = 2;

}
