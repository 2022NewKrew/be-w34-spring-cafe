package com.kakao.cafe.application.user.validation;

public class IllegalPasswordException extends IllegalArgumentException {
    public IllegalPasswordException() {
        super(UserErrorCode.PASSWORD_NOT_MATCH.getMessage());
    }
}
