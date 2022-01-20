package com.kakao.cafe.web.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String handleGlobalException(Exception e, Model model) {
        log.error(e.getMessage());
        model.addAttribute("errorMessage", e.getMessage());
        return "error";
    }
}
