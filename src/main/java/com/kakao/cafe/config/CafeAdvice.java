package com.kakao.cafe.config;

import com.kakao.cafe.exception.user.LoginFailedException;
import com.kakao.cafe.exception.article.ArticleNotFoundException;
import com.kakao.cafe.exception.user.UserNotFoundException;
import com.kakao.cafe.exception.user.UserRegisterFailedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;

@Slf4j
@ControllerAdvice
public class CafeAdvice {

    @ExceptionHandler(LoginFailedException.class)
    public String handleLoginFailedException(Exception e) {
        log.info("Login failed");
        log.info(e.getMessage());
        return "user/login";
    }

    @ExceptionHandler(UserRegisterFailedException.class)
    public String handleUserRegisterFailedException(Exception e) {
        log.info("User register failed");
        log.info(e.getMessage());
        return "error/500";
    }

    @ExceptionHandler(UserNotFoundException.class)
    public String handleUserNotFoundException(Exception e) {
        log.info("User not found");
        log.info(e.getMessage());
        return "error/500";
    }

    @ExceptionHandler(ArticleNotFoundException.class)
    public String handleArticleNotFoundException(Exception e) {
        log.info("Article not found");
        log.info(e.getMessage());
        return "error/404";
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception e) {
        log.info("Exception");
        log.info(e.getMessage());
        return "error/404";
    }
}
