package com.kakao.cafe.session.exception;

public class NoSuchUserIdException extends SessionException {
    public NoSuchUserIdException(String userId) {
        super(String.format("There is no such userId[%s].", userId));
    }
}
