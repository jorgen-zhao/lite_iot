package com.liteiot.common.exception.auth;


import com.liteiot.common.constant.CommonConstants;
import com.liteiot.common.exception.BaseException;

/**
 * 用户token异常
 */
public class UserTokenException extends BaseException {
    public UserTokenException(String message) {
        super(message, CommonConstants.EX_USER_INVALID_CODE);
    }
}
