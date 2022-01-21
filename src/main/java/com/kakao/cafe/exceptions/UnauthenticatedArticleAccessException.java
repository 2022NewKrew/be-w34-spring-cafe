package com.kakao.cafe.exceptions;

public class UnauthenticatedArticleAccessException extends PostException {

    public UnauthenticatedArticleAccessException(String message) {
        super(message);
    }
}
