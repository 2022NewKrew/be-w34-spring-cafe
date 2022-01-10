package com.kakao.cafe.error.exception.nonexist;

public class UserNotFoundedException extends NotFoundedException {
    public UserNotFoundedException(String msg) {
        super(msg);
    }
}
