package com.kakao.cafe.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String userId) {
        super("Not Found User (user id: " + userId + ")");
    }
}
