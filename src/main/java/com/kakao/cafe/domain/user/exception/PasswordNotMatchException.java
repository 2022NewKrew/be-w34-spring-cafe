package com.kakao.cafe.domain.user.exception;

public class PasswordNotMatchException extends IllegalArgumentException {

    public PasswordNotMatchException() {
        super("비밀번호가 맞지 않습니다");
    }
}
