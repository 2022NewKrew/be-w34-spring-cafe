package com.kakao.cafe.exception;

public class IllegalLoginInputException extends RuntimeException{
    public IllegalLoginInputException(){
        super();
    }
    public IllegalLoginInputException(String message){
        super(message);
    }
}
