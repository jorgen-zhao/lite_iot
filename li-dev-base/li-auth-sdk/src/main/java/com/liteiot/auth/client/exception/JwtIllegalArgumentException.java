package com.liteiot.auth.client.exception;

/**
 * JWT非法参数异常
 */
public class JwtIllegalArgumentException extends Exception {
    public JwtIllegalArgumentException(String s) {
        super(s);
    }
}
