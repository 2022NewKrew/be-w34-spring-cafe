package com.kakao.cafe.article.exception;

public class ArticleNotLoggedInException extends RuntimeException{
    public ArticleNotLoggedInException(String message){
        super(message);
    }
}
