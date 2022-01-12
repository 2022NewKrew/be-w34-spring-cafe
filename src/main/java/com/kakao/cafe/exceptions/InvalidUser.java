package com.kakao.cafe.exceptions;

public class InvalidUser extends RuntimeException {

    public InvalidUser(String errorMessage) {
        super(errorMessage);
    }
}
