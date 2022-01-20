package com.kakao.cafe.exception;

public class WrongPasswordException extends RuntimeException {

    public WrongPasswordException() {
        super("패스워드가 일치하지 않습니다.");
    }

    public WrongPasswordException(String errorMessage) {
        super(errorMessage);
    }
}
