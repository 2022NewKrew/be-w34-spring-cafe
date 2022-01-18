package com.kakao.cafe.exception;

public class ArticleNotFoundException extends RuntimeException{
    public ArticleNotFoundException(String msg) {
        super(msg);
    }
}
