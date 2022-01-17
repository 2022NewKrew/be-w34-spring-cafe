package com.kakao.cafe.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;


@ControllerAdvice
public class ControllerAdvisor {
    private final Logger logger = LoggerFactory.getLogger(ControllerAdvisor.class);

    @ExceptionHandler(CustomException.class)
    public ModelAndView generalHandler(CustomException exception) {
        ModelAndView mv = new ModelAndView("error");
        mv.getModelMap().put("message", exception.getMessage());
        mv.setStatus(HttpStatus.resolve(exception.getResponseStatus()));
        logger.error(exception.getMessage());
        return mv;
    }
}
