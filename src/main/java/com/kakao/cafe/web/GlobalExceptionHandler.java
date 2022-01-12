package com.kakao.cafe.web;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ModelAndView handleGlobalException(Exception e) {
        logger.info(e.getMessage());

        ModelAndView modelAndView = new ModelAndView("/error");
        modelAndView.addObject("errorMessage", e.getMessage());
        return modelAndView;
    }
}
