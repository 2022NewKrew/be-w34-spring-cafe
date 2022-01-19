package com.kakao.cafe.user.exception;

public class UserIdPasswordNotMatchedException extends RuntimeException {

    public UserIdPasswordNotMatchedException() {
        super("아이디 또는 패스워드가 올바르지 않습니다.");
    }
}
