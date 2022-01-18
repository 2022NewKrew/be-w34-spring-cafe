package com.kakao.cafe.common.exception;

public class ForbiddenException extends RuntimeException {

    private static final String MESSAGE = "Forbidden Error";

    public ForbiddenException() {
        super(MESSAGE);
    }
}
