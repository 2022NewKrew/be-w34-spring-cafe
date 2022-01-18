package com.kakao.cafe.article.controller;


import com.kakao.cafe.article.exception.ArticleNotLoggedInException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ArticleControllerAdvice {

    //로그인상태가 아닌경우 exception handler
    @ExceptionHandler(ArticleNotLoggedInException.class)
    public String ArticleNotLoggedInExceptionHandler(ArticleNotLoggedInException e){
        return e.getMessage();
    }
}
