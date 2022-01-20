package com.kakao.cafe.util.exception;

public class DuplicatedUserException extends RuntimeException{
    public DuplicatedUserException (String message){
        super(message);
    }
}
