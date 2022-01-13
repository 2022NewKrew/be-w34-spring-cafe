package com.kakao.cafe.web.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ModelAndView handleGlobalException(Exception e) {
        ModelAndView modelAndView = new ModelAndView("/error");
        modelAndView.addObject("errorMessage", e.getMessage());
        return modelAndView;
    }
}
