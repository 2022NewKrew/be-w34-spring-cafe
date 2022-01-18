package com.kakao.cafe.global.error.exception;

// 패스워드 불일치
public class PasswordNotMatchException extends RuntimeException{
    public PasswordNotMatchException() {
        super("비밀번호가 일치하지 않습니다.");
    }

    public PasswordNotMatchException(String message) {
        super(message);
    }
}
