package com.kakao.cafe.exceptions;

public class InvalidLoginRequestException extends UserException {

    public InvalidLoginRequestException(String message) {
        super(message);
    }
}
