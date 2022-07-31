package com.liteiot.common.exception.auth;


import com.liteiot.common.constant.CommonConstants;
import com.liteiot.common.exception.BaseException;

/**
 * 客户端禁止访问异常
 */
public class ClientForbiddenException extends BaseException {
    public ClientForbiddenException(String message) {
        super(message, CommonConstants.EX_CLIENT_FORBIDDEN_CODE);
    }

}
