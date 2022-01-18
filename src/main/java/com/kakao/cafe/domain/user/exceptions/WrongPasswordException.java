package com.kakao.cafe.domain.user.exceptions;

public class WrongPasswordException extends Exception {

    public WrongPasswordException(String message) {
        super(message);
    }
}
