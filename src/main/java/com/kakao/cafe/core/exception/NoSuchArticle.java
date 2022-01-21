package com.kakao.cafe.core.exception;

public class NoSuchArticle extends RuntimeException{
    public NoSuchArticle(String msg) {
        super(msg);
    }
}
