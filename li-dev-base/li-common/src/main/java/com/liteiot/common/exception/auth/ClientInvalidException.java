package com.liteiot.common.exception.auth;


import com.liteiot.common.constant.CommonConstants;
import com.liteiot.common.exception.BaseException;

/**
 * 客户端无效异常
 */
public class ClientInvalidException extends BaseException {
    public ClientInvalidException(String message) {
        super(message, CommonConstants.EX_CLIENT_INVALID_CODE);
    }
}
