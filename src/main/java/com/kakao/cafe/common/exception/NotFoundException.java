package com.kakao.cafe.common.exception;

public class NotFoundException extends RuntimeException {

    private static final String MESSAGE = "No Resource Error: ";

    public NotFoundException(String message) {
        super(MESSAGE + message);
    }
}
