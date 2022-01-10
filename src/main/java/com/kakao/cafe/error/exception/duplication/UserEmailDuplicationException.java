package com.kakao.cafe.error.exception.duplication;

public class UserEmailDuplicationException extends DuplicationException {
    public UserEmailDuplicationException(String msg) {
        super(msg);
    }
}
