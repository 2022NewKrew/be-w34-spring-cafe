package com.kakao.cafe.error.exception.nonexist;

public class SessionAttributesNotFoundedException extends NotFoundedException {
    public SessionAttributesNotFoundedException(String msg) {
        super(msg);
    }
}
