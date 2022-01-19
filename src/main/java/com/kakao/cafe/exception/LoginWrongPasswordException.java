package com.kakao.cafe.exception;

public class LoginWrongPasswordException extends RuntimeException {
    public LoginWrongPasswordException() {
        super("비밀번호를 잘못 입력했습니다.");
    }
}
