package com.kakao.cafe.core.exception;

public class AlreadyExistId extends RuntimeException{
    public AlreadyExistId(String msg) {
        super(msg);
    }
}
