package com.kakao.cafe.error.exception;

import com.kakao.cafe.error.ErrorCode;

public class ForbiddenAccessException extends RuntimeException {

    private static final String MESSAGE_FORMAT = "Forbidden Access For %s";
    private final ErrorCode errorCode;

    public ForbiddenAccessException(ErrorCode errorCode, String accessPoint) {
        super(String.format(MESSAGE_FORMAT, accessPoint));
        this.errorCode = errorCode;
    }
}
