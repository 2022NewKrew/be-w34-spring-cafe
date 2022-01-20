package com.kakao.cafe.exception;

public class NoAuthenticationException extends RuntimeException {

    public NoAuthenticationException() {
        super("로그인하지 않은 사용자입니다.");
    }
}
