package com.kakao.cafe.user.controller;

import com.kakao.cafe.article.exception.ArticleNotMatchedUser;
import com.kakao.cafe.user.exception.UserNotMatchedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserControllerAdvice {

    @ExceptionHandler(UserNotMatchedException.class)
    public String UserNotMatchedWithLoggedInUserId(UserNotMatchedException e){
        return e.getMessage();
    }
}
