package com.kakao.cafe.exception.user;

public class LoginFailedException extends UserException {
    public LoginFailedException() {
        super();
    }

    public LoginFailedException(String message) {
        super(message);
    }
}
