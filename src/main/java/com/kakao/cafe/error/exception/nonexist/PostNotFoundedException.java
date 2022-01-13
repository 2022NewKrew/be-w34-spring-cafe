package com.kakao.cafe.error.exception.nonexist;

public class PostNotFoundedException extends NotFoundedException {
    public PostNotFoundedException(String msg) {
        super(msg);
    }
}
