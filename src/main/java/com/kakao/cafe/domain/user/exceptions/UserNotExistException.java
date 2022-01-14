package com.kakao.cafe.domain.user.exceptions;

public class UserNotExistException extends Exception {

    public UserNotExistException(String message) {
        super(message);
    }
}
