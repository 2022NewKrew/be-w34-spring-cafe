package com.kakao.cafe.exception;

public class NoSuchArticle extends RuntimeException{
    public NoSuchArticle(String msg) {
        super(msg);
    }
}
