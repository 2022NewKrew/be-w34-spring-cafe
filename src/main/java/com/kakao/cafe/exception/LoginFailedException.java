package com.kakao.cafe.exception;

public class LoginFailedException extends RuntimeException {
    public final ErrorCode errorCode;

    public LoginFailedException(ErrorCode errorCode) {
        super(errorCode.getErrorMessage());
        this.errorCode = errorCode;
    }
}
