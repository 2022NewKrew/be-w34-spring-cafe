package com.kakao.cafe.common.exception.custom;

import com.kakao.cafe.common.exception.data.ErrorCode;

public class UserNotFoundException extends RuntimeException implements CustomException {

    private final ErrorCode errorCode;

    public UserNotFoundException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public UserNotFoundException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    @Override
    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
