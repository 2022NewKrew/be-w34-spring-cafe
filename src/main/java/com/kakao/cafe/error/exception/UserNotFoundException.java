package com.kakao.cafe.error.exception;

import com.kakao.cafe.error.ErrorCode;

public class UserNotFoundException extends RuntimeException {

    private static final String MESSAGE_FORMAT = "Not Found User [UID : %s]";
    private final ErrorCode errorCode;

    public UserNotFoundException(ErrorCode errorCode, String userId) {
        super(String.format(MESSAGE_FORMAT, userId));
        this.errorCode = errorCode;
    }
}
