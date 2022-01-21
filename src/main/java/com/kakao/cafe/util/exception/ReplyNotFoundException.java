package com.kakao.cafe.util.exception;

public class ReplyNotFoundException extends RuntimeException{
    public ReplyNotFoundException(String message){
        super(message);
    }
}
