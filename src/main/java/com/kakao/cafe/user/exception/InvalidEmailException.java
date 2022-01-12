package com.kakao.cafe.user.exception;

public class InvalidEmailException extends UserException {
    private final String invalidEmail;

    public InvalidEmailException(String invalidEmail) {
        this.invalidEmail = invalidEmail;
    }

    public String getInvalidEmail() {
        return invalidEmail;
    }
}
