package com.kakao.cafe.controller;

import com.kakao.cafe.exception.NotAuthorizedException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler(IllegalArgumentException.class)
    public String handleIllegalArgumentException(IllegalArgumentException e, Model model) {
        model.addAttribute("message", e.getMessage());
        return "error";
    }

    @ExceptionHandler(NotAuthorizedException.class)
    public String handleNotAuthorizedException(NotAuthorizedException e, Model model) {
        model.addAttribute("message", e.getMessage());
        return "error";
    }
}
