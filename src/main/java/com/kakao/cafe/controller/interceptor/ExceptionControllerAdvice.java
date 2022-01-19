package com.kakao.cafe.controller.interceptor;

import com.kakao.cafe.domain.user.exception.AnonymousUserException;
import com.kakao.cafe.domain.user.exception.UserLoginFailedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.sql.SQLException;

@ControllerAdvice
public class ExceptionControllerAdvice {

    private final Logger logger = LoggerFactory.getLogger(ExceptionControllerAdvice.class);

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(IllegalArgumentException.class)
    public String error(IllegalArgumentException e, Model model) {
        logger.info("Error : {} ", e.getMessage());
        model.addAttribute("message", e.getMessage());
        return "error";
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserLoginFailedException.class)
    public String loginError(UserLoginFailedException e, Model model) {
        model.addAttribute("message", e.getMessage());
        return "user/login";
    }

    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AnonymousUserException.class)
    public String anonymousUserError(AnonymousUserException e, Model model) {
        model.addAttribute("message", e.getMessage());
        return "user/login";
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(SQLException.class)
    public String error(SQLException e, Model model) {
        logger.info("Error : {} ", e.getMessage());
        model.addAttribute("message", "예상치 못한 오류가 발생했습니다.");
        return "error";
    }
}
