package com.kakao.cafe.config;

import com.kakao.cafe.exception.SessionUserNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SessionUserNotFoundException.class)
    public String handleSessionUserNotFoundException(SessionUserNotFoundException e) {
        return "redirect:/users/login";
    }
}
