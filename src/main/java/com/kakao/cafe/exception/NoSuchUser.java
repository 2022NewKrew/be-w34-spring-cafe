package com.kakao.cafe.exception;

public class NoSuchUser extends RuntimeException{
    public NoSuchUser(String msg) {
        super(msg);
    }
}
