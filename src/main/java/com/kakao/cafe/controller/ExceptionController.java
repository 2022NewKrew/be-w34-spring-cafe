package com.kakao.cafe.controller;

import com.kakao.cafe.exceptions.NoSuchArticleException;
import com.kakao.cafe.exceptions.NoSuchUserException;
import com.kakao.cafe.exceptions.PasswordMismatchException;
import com.kakao.cafe.exceptions.UserIdDuplicationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler({NoSuchUserException.class, NoSuchArticleException.class, PasswordMismatchException.class, UserIdDuplicationException.class})
    public String exceptionHandler() {
        return "errorCaughtByControllerAdvice";
    }
}
