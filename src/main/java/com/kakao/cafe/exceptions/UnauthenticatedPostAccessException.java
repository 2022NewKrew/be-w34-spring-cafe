package com.kakao.cafe.exceptions;

public class UnauthenticatedPostAccessException extends PostException {

    public UnauthenticatedPostAccessException(String message) {
        super(message);
    }
}
