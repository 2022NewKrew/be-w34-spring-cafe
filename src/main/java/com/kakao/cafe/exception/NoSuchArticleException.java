package com.kakao.cafe.exception;

public class NoSuchArticleException extends RuntimeException {

    public NoSuchArticleException(String message) {
        super(message);
    }
}
