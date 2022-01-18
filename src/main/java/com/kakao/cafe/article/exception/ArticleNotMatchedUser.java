package com.kakao.cafe.article.exception;

public class ArticleNotMatchedUser extends RuntimeException{
    public ArticleNotMatchedUser(String message){
        super(message);
    }
}
