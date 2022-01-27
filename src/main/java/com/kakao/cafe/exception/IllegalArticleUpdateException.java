package com.kakao.cafe.exception;

public class IllegalArticleUpdateException extends RuntimeException {

    public IllegalArticleUpdateException() {
        super();
    }

    public IllegalArticleUpdateException(String message) {
        super(message);
    }
}
