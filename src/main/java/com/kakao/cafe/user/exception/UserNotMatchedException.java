package com.kakao.cafe.user.exception;

public class UserNotMatchedException extends RuntimeException{
    public UserNotMatchedException(String message){
        super(message);
    }
}
