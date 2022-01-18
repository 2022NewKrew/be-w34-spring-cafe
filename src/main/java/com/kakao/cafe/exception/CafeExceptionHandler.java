package com.kakao.cafe.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CafeExceptionHandler {

    Logger logger = LoggerFactory.getLogger(CafeExceptionHandler.class);

    @ExceptionHandler(CustomException.class)
    public String handleCustomException(CustomException e, Model model) {
        model.addAttribute("errorStatus", e.getErrorCode().getHttpStatus());
        model.addAttribute("errorMessage", e.getMessage());
        return "error";
    }
}
