package com.liteiot.common.exception.auth;


import com.liteiot.common.constant.CommonConstants;
import com.liteiot.common.exception.BaseException;

/**
 * 用户无效异常
 */
public class UserInvalidException extends BaseException {
    public UserInvalidException(String message) {
        super(message, CommonConstants.EX_USER_PASS_INVALID_CODE);
    }
}
