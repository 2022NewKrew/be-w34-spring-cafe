package com.kakao.cafe.user.controller;

import com.kakao.cafe.Interceptor.ApiError;
import com.kakao.cafe.article.exception.ArticleNotMatchedUser;
import com.kakao.cafe.user.exception.UserNotMatchedException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserControllerAdvice {

    //클라이언트에서의 요청한 userId와 session에서의 userId가 일치하지 않았을 경우
    @ExceptionHandler(UserNotMatchedException.class)
    public String UserNotMatchedWithLoggedInUserId(UserNotMatchedException e, Model model){
        model.addAttribute("apiError", new ApiError(HttpStatus.UNAUTHORIZED, e.getMessage()));
        return "/error/errorpage";
    }


}
