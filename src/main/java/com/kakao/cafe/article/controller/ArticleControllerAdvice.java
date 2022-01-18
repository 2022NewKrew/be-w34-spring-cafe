package com.kakao.cafe.article.controller;


import com.kakao.cafe.article.exception.ArticleNotLoggedInException;
import com.kakao.cafe.article.exception.ArticleNotMatchedUser;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ArticleControllerAdvice {

    //로그인상태가 아닌경우 exception handler
    @ExceptionHandler(ArticleNotLoggedInException.class)
    public String ArticleNotLoggedInExceptionHandler(ArticleNotLoggedInException e){
        return e.getMessage();
    }


    //session에 로그인된 유저와 form에서 전달받은 userId가 매칭되지 않는경우
    @ExceptionHandler(ArticleNotMatchedUser.class)
    public String ArticleNotMatchedWithLoggedInUserId(ArticleNotMatchedUser e){
        return e.getMessage();
    }
}
