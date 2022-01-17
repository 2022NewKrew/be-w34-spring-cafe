package com.kakao.cafe.web;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionController {
    @org.springframework.web.bind.annotation.ExceptionHandler(IllegalArgumentException.class)
    protected String handleAccessDeniedException(IllegalArgumentException e, Model model) {
        model.addAttribute("exception", e);
        return "errors/400";
    }
}
