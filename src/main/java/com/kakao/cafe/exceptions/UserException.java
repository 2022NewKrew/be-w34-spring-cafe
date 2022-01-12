package com.kakao.cafe.exceptions;

public abstract class UserException extends RuntimeException {

    public UserException(String message) {
        super(message);
    }
}
