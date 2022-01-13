package com.kakao.cafe.error.exception;

import com.kakao.cafe.error.ErrorCode;

public class AuthInvalidPasswordException extends RuntimeException {

    private static final String MESSAGE_FORMAT = "Invalid Password for User [UID : %s]";
    private final ErrorCode errorCode;

    public AuthInvalidPasswordException(ErrorCode errorCode, String uid) {
        super(String.format(MESSAGE_FORMAT, uid));
        this.errorCode = errorCode;
    }
}
