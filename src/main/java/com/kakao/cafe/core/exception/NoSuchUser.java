package com.kakao.cafe.core.exception;

public class NoSuchUser extends RuntimeException{
    public NoSuchUser(String msg) {
        super(msg);
    }
    public NoSuchUser() {
        super();
    }
}
