package com.kakao.cafe.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
@Slf4j
public class ControllerAdvisor {

    @ExceptionHandler(InvalidUsernamePasswordException.class)
    public ModelAndView retryOnLoginFailure(InvalidUsernamePasswordException exception) {
        log.error(exception.getMessage());
        ModelAndView mv = new ModelAndView("user/login-failed");
        mv.setStatus(exception.getResponseStatus());
        return mv;
    }

    @ExceptionHandler(CustomException.class)
    public ModelAndView definedExceptionHandler(CustomException exception) {
        log.error(exception.getMessage());
        ModelAndView mv = new ModelAndView("common/custom-error");
        mv.getModelMap().put("message", exception.getMessage());
        mv.setStatus(exception.getResponseStatus());
        return mv;
    }

    // Exceptions other than CustomException is handled by Spring Boot with default whitelabel page
}
