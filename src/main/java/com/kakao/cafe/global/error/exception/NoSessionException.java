package com.kakao.cafe.global.error.exception;

public class NoSessionException extends RuntimeException{
    public NoSessionException() {
        super("로그인 세션이 존재하지 않습니다.");
    }

    public NoSessionException(String message) {
        super(message);
    }
}
