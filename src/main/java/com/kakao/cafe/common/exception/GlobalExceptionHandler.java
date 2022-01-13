package com.kakao.cafe.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ModelAndView handleBusinessException(BusinessException exception){
        ErrorType errorType = exception.getErrorType();
        log.info("[{}] 비즈니스 에러 발생 : {}", errorType.getCode(), errorType.getMessage());

        ModelAndView modelAndView = new ModelAndView("/error");
        modelAndView.addObject("errorMessage", errorType.getMessage());

        return modelAndView;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception exception){
        log.info("에러 발생");
        log.info("{}", exception.getMessage());
        ModelAndView modelAndView = new ModelAndView("/error");
        modelAndView.addObject("errorMessage", exception.getMessage());

        return modelAndView;
    }
}
