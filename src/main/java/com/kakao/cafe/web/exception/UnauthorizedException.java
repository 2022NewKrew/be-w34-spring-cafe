package com.kakao.cafe.web.exception;

public class UnauthorizedException extends RuntimeException {
    public final static String message = "로그인이 필요합니다.";

    public UnauthorizedException() {
        super(message);
    }
}
