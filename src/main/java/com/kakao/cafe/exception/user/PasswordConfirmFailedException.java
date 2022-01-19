package com.kakao.cafe.exception.user;

public class PasswordConfirmFailedException extends UserException {
    public PasswordConfirmFailedException() {
        super();
    }

    public PasswordConfirmFailedException(String message) {
        super(message);
    }
}
