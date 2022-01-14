package com.kakao.cafe.domain.user.exceptions;

public class IllegalPasswordException extends Exception {

    public IllegalPasswordException(String message) {
        super(message);
    }
}
