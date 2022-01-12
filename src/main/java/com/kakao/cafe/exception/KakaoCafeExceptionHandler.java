package com.kakao.cafe.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class KakaoCafeExceptionHandler {
    Logger logger = LoggerFactory.getLogger(KakaoCafeExceptionHandler.class);

    @ExceptionHandler(IllegalStateException.class)
    public Object IllegalState(Exception e){
        logger.info(e.getMessage());
        return "redirect:/";
    }
}
