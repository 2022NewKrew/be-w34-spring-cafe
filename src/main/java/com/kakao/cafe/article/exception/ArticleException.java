package com.kakao.cafe.article.exception;

public abstract class ArticleException extends RuntimeException {
    public ArticleException(String message) {
        super(message);
    }
}
