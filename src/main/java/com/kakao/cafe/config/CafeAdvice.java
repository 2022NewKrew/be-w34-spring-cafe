package com.kakao.cafe.config;

import com.kakao.cafe.exception.user.LoginFailedException;
import com.kakao.cafe.exception.article.ArticleNotFoundException;
import com.kakao.cafe.exception.user.UserNotFoundException;
import com.kakao.cafe.exception.user.UserRegisterFailedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class CafeAdvice {

    @ExceptionHandler(LoginFailedException.class)
    public String handleLoginFailedException() {
        log.info("Login failed");
        return "user/login";
    }

    @ExceptionHandler(UserRegisterFailedException.class)
    public String handleUserRegisterFailedException() {
        log.info("User register failed");
        return "error/500";
    }

    @ExceptionHandler(UserNotFoundException.class)
    public String handleUserNotFoundException() {
        log.info("User not found");
        return "error/500";
    }

    @ExceptionHandler(ArticleNotFoundException.class)
    public String handleArticleNotFoundException() {
        log.info("Article not found");
        return "error/404";
    }

    @ExceptionHandler(Exception.class)
    public String handleException() {
        log.info("Exception");
        return "error/404";
    }
}
