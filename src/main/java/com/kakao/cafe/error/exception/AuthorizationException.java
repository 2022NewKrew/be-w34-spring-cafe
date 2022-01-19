package com.kakao.cafe.error.exception;

public class AuthorizationException extends RuntimeException{
    private static final String MESSAGE = "권한이 없습니다.";

    public AuthorizationException() {
        super(MESSAGE);
    }
}
