package com.kakao.cafe.exception;

public class NoAuthorizationException extends RuntimeException {
    public NoAuthorizationException(String msg) {
        super(msg);
    }
}
