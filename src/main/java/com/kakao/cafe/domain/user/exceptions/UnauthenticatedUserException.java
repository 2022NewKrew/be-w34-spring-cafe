package com.kakao.cafe.domain.user.exceptions;

public class UnauthenticatedUserException extends Exception {

    public UnauthenticatedUserException(String message) {
        super(message);
    }
}
