package com.kakao.cafe.exception;

public class UnauthenticatedArticleAccessException extends RuntimeException {

    public UnauthenticatedArticleAccessException(String message) {
        super(message);
    }
}
