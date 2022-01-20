package com.kakao.cafe.article.exception;

public class AuthorNotMatchedException extends ArticleException {
    public AuthorNotMatchedException(String message) {
        super(message);
    }
}
