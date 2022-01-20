package com.kakao.cafe.interfaces.user.exception;

public class UnauthorizedUserException extends IllegalAccessException {
    public UnauthorizedUserException() {
        super("올바르지 않은 접근입니다.");
    }
}
