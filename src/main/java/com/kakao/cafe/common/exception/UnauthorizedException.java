package com.kakao.cafe.common.exception;

public class UnauthorizedException extends RuntimeException {

    private static final String MESSAGE = "Unauthorized Error: ";

    public UnauthorizedException(String message) {
        super(MESSAGE + message);
    }
}
