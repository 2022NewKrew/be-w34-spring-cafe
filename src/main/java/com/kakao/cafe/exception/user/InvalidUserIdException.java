package com.kakao.cafe.exception.user;

public class InvalidUserIdException extends UserException {
    private final String invalidUserId;

    public InvalidUserIdException(String invalidUserId) {
        this.invalidUserId = invalidUserId;
    }

    public String getInvalidUserId() {
        return invalidUserId;
    }
}
