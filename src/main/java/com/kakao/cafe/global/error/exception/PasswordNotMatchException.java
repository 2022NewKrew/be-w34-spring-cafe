package com.kakao.cafe.global.error.exception;

// 패스워드 불일치
public class PasswordNotMatchException extends RuntimeException{
    public PasswordNotMatchException() {
    }

    public PasswordNotMatchException(String message) {
        super(message);
    }
}
