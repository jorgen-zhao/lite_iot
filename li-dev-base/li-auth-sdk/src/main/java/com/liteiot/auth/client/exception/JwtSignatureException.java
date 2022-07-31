package com.liteiot.auth.client.exception;

/**
 * JWT签名异常
 */
public class JwtSignatureException extends Exception {
    public JwtSignatureException(String s) {
        super(s);
    }
}
