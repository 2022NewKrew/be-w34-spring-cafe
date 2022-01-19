package com.kakao.cafe.error.exception;

public class LoginException extends RuntimeException{
    private static final String MESSAGE = "로그인이 필요합니다.";

    public LoginException() {
        super(MESSAGE);
    }
}
