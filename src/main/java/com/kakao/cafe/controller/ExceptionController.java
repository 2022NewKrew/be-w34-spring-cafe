package com.kakao.cafe.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(Exception.class)
    public String exceptionHandler() {
        return "errorCaughtByControllerAdvice";
    }
}
