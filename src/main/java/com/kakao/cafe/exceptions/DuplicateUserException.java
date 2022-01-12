package com.kakao.cafe.exceptions;

public class DuplicateUserException extends UserException {

    public DuplicateUserException(String message) {
        super(message);
    }
}
