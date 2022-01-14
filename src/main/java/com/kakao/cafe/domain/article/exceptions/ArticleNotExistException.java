package com.kakao.cafe.domain.article.exceptions;

public class ArticleNotExistException extends Exception {

    public ArticleNotExistException(String message) {
        super(message);
    }
}
