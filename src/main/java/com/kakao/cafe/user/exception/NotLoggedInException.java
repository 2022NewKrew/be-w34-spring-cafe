package com.kakao.cafe.user.exception;

public class NotLoggedInException extends RuntimeException {

    public NotLoggedInException() {
        super("로그인 되어 있지 않습니다.");
    }
}
