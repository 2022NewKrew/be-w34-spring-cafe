package com.kakao.cafe.user.mapper.exception;

public class ForbiddenException extends RuntimeException {
    public ForbiddenException() {
        super("권한이 없습니다.");
    }
}
