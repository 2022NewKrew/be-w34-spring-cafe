package com.kakao.cafe.exception;

public class UpdateForbiddenException extends Exception{
    public UpdateForbiddenException(){
        super();
    }

    public UpdateForbiddenException(String msg){
        super(msg);
    }
}
