package com.kakao.cafe.exception;

public class UnAuthorizedException extends CustomException{
    public UnAuthorizedException(String msg){
        super(msg);
    }
}
