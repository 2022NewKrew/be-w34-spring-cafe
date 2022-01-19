package com.kakao.cafe.domain.user.exception;

public class IllegalEmailFormatException extends IllegalArgumentException{
    public IllegalEmailFormatException(String message) {
        super(message);
    }
}
