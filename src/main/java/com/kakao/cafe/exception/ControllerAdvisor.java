package com.kakao.cafe.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;


@ControllerAdvice
public class ControllerAdvisor {
    private final Logger logger = LoggerFactory.getLogger(ControllerAdvisor.class);

    @ExceptionHandler(CustomException.class)
    public ModelAndView definedExceptionHandler(CustomException exception) {
        ModelAndView mv = new ModelAndView("common/custom-error");
        mv.getModelMap().put("message", exception.getMessage());
        mv.setStatus(exception.getResponseStatus());
        logger.info(exception.getMessage());
        return mv;
    }

    // Exception other than custom class use Spring boot's default whitelabel page
}
