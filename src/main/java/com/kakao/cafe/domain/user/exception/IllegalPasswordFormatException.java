package com.kakao.cafe.domain.user.exception;

public class IllegalPasswordFormatException extends IllegalArgumentException{
    public IllegalPasswordFormatException(String name) {
        super(name);
    }
}
