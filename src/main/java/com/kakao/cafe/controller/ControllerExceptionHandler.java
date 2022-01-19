package com.kakao.cafe.controller;

import com.kakao.cafe.exception.ArticleNotFoundException;
import com.kakao.cafe.exception.NoAuthorizationException;
import com.kakao.cafe.exception.NoLoginException;
import com.kakao.cafe.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(NoLoginException.class)
    public String noLoginException(Exception e, Model model) {
        model.addAttribute("errorMessage", e.getMessage());
        return "error";
    }

    @ExceptionHandler(NoAuthorizationException.class)
    public String noAuthorizationException(Exception e, Model model) {
        model.addAttribute("errorMessage", e.getMessage());
        return "error";
    }

    @ExceptionHandler(ArticleNotFoundException.class)
    public String articleNotFoundException(Exception e, Model model) {
        model.addAttribute("errorMessage", e.getMessage());
        return "error";
    }

    @ExceptionHandler(UserNotFoundException.class)
    public String userNotFoundException(Exception e, Model model) {
        model.addAttribute("errorMessage", e.getMessage());
        return "error";
    }

    @ExceptionHandler(Exception.class)
    public String otherException(Exception e, Model model){
        model.addAttribute("errorMessage", e.getMessage());
        return "error";
    }
}
