package com.kakao.cafe.error.exception;

import com.kakao.cafe.error.ErrorCode;

public class AuthInvalidUidException extends RuntimeException {

    private static final String MESSAGE_FORMAT = "Not Found [UID : %s]";
    private final ErrorCode errorCode;

    public AuthInvalidUidException(ErrorCode errorCode, String uid) {
        super(String.format(MESSAGE_FORMAT, uid));
        this.errorCode = errorCode;
    }
}
