package com.kakao.cafe.user.exception;

public class PasswordNotMatchedException extends RuntimeException {

    public PasswordNotMatchedException() {
        super("패스워드가 맞지 않습니다.");
    }
}
