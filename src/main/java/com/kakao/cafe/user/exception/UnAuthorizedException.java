package com.kakao.cafe.user.exception;

public class UnAuthorizedException extends RuntimeException {

    public UnAuthorizedException() {
        super("로그인을 해주세요");
    }
}
