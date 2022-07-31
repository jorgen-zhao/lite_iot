package com.liteiot.common.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 基类: 异常
 */
@Data
@NoArgsConstructor
public class BaseException extends RuntimeException {

    private int status = 500;

    public BaseException(String message, int status) {
        super(message);
        this.status = status;
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

    public BaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
