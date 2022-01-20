package com.kakao.cafe.article.controller;


import com.kakao.cafe.Interceptor.ApiError;
import com.kakao.cafe.article.exception.ArticleNotLoggedInException;
import com.kakao.cafe.article.exception.ArticleNotMatchedUser;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ArticleControllerAdvice {

    //로그인상태가 아닌경우
    @ExceptionHandler(ArticleNotLoggedInException.class)
    public String ArticleNotLoggedInExceptionHandler(ArticleNotLoggedInException e, Model model){
        model.addAttribute("apiError", new ApiError(HttpStatus.FORBIDDEN, e.getMessage()));
        return "/error/errorpage";
    }


    //session에 로그인된 유저와 클라이언트에서 요청받은 userId가 매칭되지 않는경우
    @ExceptionHandler({ArticleNotMatchedUser.class})
    public String ArticleNotMatchedWithLoggedInUserId(ArticleNotMatchedUser e, Model model){
        model.addAttribute("apiError", new ApiError(HttpStatus.UNAUTHORIZED, e.getMessage()));
        return "/error/errorpage";
    }
}
