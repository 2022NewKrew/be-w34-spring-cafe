package com.kakao.cafe.domain.user.exception;

public class UserNotMathException extends IllegalArgumentException {

    public UserNotMathException() {
        super("권한이 있는 사용자가 아닙니다.");
    }
}
