package com.kakao.cafe.global.error.exception;

public class PasswordNotMatchException extends RuntimeException{
    public PasswordNotMatchException() {
    }

    public PasswordNotMatchException(String message) {
        super(message);
    }
}
