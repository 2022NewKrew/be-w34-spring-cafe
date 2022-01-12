package com.kakao.cafe.error.exception;

import com.kakao.cafe.error.ErrorCode;

public class AuthInvalidUidException extends RuntimeException {

    private static final String MESSAGE_FORMAT = "User [UID : %s] %s";
    private final ErrorCode errorCode;

    public AuthInvalidUidException(ErrorCode errorCode, String uid) {
        super(String.format(MESSAGE_FORMAT, errorCode.getErrorMessage(), uid));
        this.errorCode = errorCode;
    }
}
