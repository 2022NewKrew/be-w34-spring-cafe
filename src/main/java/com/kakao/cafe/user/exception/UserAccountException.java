package com.kakao.cafe.user.exception;

public class UserAccountException extends RuntimeException {

    private final UserAccountErrorCode errorCode;

    public UserAccountException(UserAccountErrorCode errorCode) {
        super(errorCode.getDescription());
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode.getCode();
    }

    public String getErrorMessage() {
        return errorCode.getDescription();
    }
}
