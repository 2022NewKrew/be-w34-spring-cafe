package com.kakao.cafe.exception;

public class LoginUserNotFoundException extends RuntimeException {
    private final String message;

    public LoginUserNotFoundException(String userId) {
        this.message = "Not Found Login User (user id: " + userId + ")";
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
