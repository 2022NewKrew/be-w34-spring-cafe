package com.kakao.cafe.error.exception;

import com.kakao.cafe.error.ErrorCode;

public class UserInvalidAuthInfoException extends RuntimeException {

    private static final String MESSAGE_FORMAT = "Invalid Auth Info for User [UID : %s]";
    private final ErrorCode errorCode;

    public UserInvalidAuthInfoException(ErrorCode errorCode, String uid) {
        super(String.format(MESSAGE_FORMAT, uid));
        this.errorCode = errorCode;
    }
}
