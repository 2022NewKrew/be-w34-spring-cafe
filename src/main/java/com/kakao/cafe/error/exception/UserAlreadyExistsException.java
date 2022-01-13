package com.kakao.cafe.error.exception;

import com.kakao.cafe.error.ErrorCode;

public class UserAlreadyExistsException extends RuntimeException {

    private static final String MESSAGE_FORMAT = "Already Exists User [UID : %s]";
    private final ErrorCode errorCode;

    public UserAlreadyExistsException(ErrorCode errorCode, String userId) {
        super(String.format(MESSAGE_FORMAT, userId));
        this.errorCode = errorCode;
    }
}
