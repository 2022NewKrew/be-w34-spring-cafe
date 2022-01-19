package com.kakao.cafe.domain.user.exception;

public class IllegalUserIdException extends IllegalArgumentException{
    public IllegalUserIdException(String name) {
        super(name);
    }
}
