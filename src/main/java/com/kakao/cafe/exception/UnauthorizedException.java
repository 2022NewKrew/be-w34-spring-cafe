package com.kakao.cafe.exception;

public class UnauthorizedException extends RuntimeException {

    public UnauthorizedException(String s) {
        super(s);
    }
}
