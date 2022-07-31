package com.liteiot.auth.client.exception;

/**
 * JWT token过期异常
 */
public class JwtTokenExpiredException extends Exception {
    public JwtTokenExpiredException(String s) {
        super(s);
    }
}
