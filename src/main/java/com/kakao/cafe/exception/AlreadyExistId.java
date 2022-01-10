package com.kakao.cafe.exception;

public class AlreadyExistId extends RuntimeException{
    public AlreadyExistId(String msg) {
        super(msg);
    }
}
