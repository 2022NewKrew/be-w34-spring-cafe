package com.example.kakaocafe.core.exception;

public class SignUpException extends BusinessException{
    public SignUpException() {
        super("이미 등록된 아이디 입니다");
    }
}
