package com.kakao.cafe.exception.user;

public class UserRegisterFailedException extends UserException {
    public UserRegisterFailedException() {
        super();
    }

    public UserRegisterFailedException(String message) {
        super(message);
    }
}
