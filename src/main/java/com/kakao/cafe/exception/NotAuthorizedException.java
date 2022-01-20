package com.kakao.cafe.exception;

public class NotAuthorizedException extends RuntimeException {
    public NotAuthorizedException(String msg){
        super(msg);
    }
}
