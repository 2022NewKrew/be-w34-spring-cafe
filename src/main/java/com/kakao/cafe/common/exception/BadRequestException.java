package com.kakao.cafe.common.exception;

public class BadRequestException extends RuntimeException {

    private static final String MESSAGE = "Bad Request Error: ";

    public BadRequestException(String message) {
        super(MESSAGE + message);
    }
}
