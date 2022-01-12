package com.kakao.cafe.error.exception;

import com.kakao.cafe.error.ErrorCode;

public class AuthInvalidPasswordException extends RuntimeException {

    private static final String MESSAGE_FORMAT = "%s password for User [UID : %s]";
    private final ErrorCode errorCode;

    public AuthInvalidPasswordException(ErrorCode errorCode, String uid) {
        super(String.format(MESSAGE_FORMAT, errorCode.getErrorMessage(), uid));
        this.errorCode = errorCode;
    }
}
