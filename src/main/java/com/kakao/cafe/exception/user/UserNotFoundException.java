package com.kakao.cafe.exception.user;

public class UserNotFoundException extends UserException {
    public UserNotFoundException() {
        super();
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
