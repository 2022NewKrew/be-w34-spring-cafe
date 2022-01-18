package com.kakao.cafe.domain.user.exception;

public class IllegalUserIdFormatException extends IllegalUserIdException{
    public IllegalUserIdFormatException(String name) {
        super(name);
    }
}
