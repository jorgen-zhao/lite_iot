package com.liteiot.common.exception.auth;


import com.liteiot.common.constant.CommonConstants;
import com.liteiot.common.exception.BaseException;

/**
 * 客户端token异常
 */
public class ClientTokenException extends BaseException {
    public ClientTokenException(String message) {
        super(message, CommonConstants.EX_CLIENT_INVALID_CODE);
    }
}
