package com.kakao.cafe.user.exception;

public class LoginFailException extends RuntimeException {

    public LoginFailException() {
        super("로그인 실패하였습니다");
    }
}
