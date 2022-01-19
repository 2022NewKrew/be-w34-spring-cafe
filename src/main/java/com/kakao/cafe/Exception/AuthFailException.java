package com.kakao.cafe.Exception;

public class AuthFailException extends RuntimeException{
    public AuthFailException(String message) {
        super(message);
    }
}
