package com.kakao.cafe.user.exception;

public class UserIdDuplicateException extends RuntimeException {

    public UserIdDuplicateException() {
        super("이미 존재하는 아이디입니다.");
    }
}
