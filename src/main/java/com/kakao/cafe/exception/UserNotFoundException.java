package com.kakao.cafe.exception;

public class UserNotFoundException extends RuntimeException {
    private final String message;

    public UserNotFoundException(String userId) {
        this.message = "Not Found User (user id: " + userId + ")";
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
