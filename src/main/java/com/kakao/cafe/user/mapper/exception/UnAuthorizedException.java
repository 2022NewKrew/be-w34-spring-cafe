package com.kakao.cafe.user.mapper.exception;

public class UnAuthorizedException extends RuntimeException {

    public UnAuthorizedException() {
        super("로그인 되어 있지 않습니다.");
    }
}
