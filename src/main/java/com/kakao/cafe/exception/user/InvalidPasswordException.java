package com.kakao.cafe.exception.user;

public class InvalidPasswordException extends UserException {
    private final String invalidPassword;

    public InvalidPasswordException(String invalidPassword) {
        this.invalidPassword = invalidPassword;
    }

    public String getInvalidPassword() {
        return invalidPassword;
    }
}
