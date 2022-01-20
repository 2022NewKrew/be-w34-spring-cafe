package com.kakao.cafe.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler{

    @ExceptionHandler(IllegalArgumentException.class)
    public String handleLoginException(IllegalArgumentException e, Model model){
        model.addAttribute("errorMessage", e.getMessage());
        return "error";
    }
}
