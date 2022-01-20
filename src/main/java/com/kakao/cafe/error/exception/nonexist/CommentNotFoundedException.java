package com.kakao.cafe.error.exception.nonexist;

public class CommentNotFoundedException extends NotFoundedException {
    public CommentNotFoundedException(String msg) {
        super(msg);
    }
}
