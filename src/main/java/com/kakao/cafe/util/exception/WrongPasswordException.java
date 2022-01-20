package com.kakao.cafe.util.exception;

public class WrongPasswordException extends RuntimeException{
    public WrongPasswordException (String message){
        super(message);
    }
}
