package com.kakao.cafe.domain.user.exception;

public class IllegalUserIdLengthException extends IllegalUserIdException{
    public IllegalUserIdLengthException(String name) {
        super(name);
    }
}
