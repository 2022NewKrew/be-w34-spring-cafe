package com.kakao.cafe.user.exception;

public class InvalidNameException extends UserException {
    private final String invalidName;

    public InvalidNameException(String invalidName) {
        this.invalidName = invalidName;
    }

    public String getInvalidName() {
        return invalidName;
    }
}
