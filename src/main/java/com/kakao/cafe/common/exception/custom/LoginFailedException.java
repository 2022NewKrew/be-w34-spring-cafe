package com.kakao.cafe.common.exception.custom;

import com.kakao.cafe.common.exception.data.ErrorCode;

public class LoginFailedException extends RuntimeException implements CustomException {

    private final ErrorCode errorCode;

    public LoginFailedException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public LoginFailedException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    @Override
    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
