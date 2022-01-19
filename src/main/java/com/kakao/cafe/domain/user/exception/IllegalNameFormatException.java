package com.kakao.cafe.domain.user.exception;

public class IllegalNameFormatException extends IllegalArgumentException{
    public IllegalNameFormatException(String name) {
        super(name);
    }
}
