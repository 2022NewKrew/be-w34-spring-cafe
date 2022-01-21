package com.kakao.cafe.exception;

public class LoginUserNotFoundException extends RuntimeException {
    public LoginUserNotFoundException() {
        super("Not Found Login User");
    }

    public LoginUserNotFoundException(String userId) {
        super("Not Found Login User (user id: " + userId + ")");
    }
}
