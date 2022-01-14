package com.kakao.cafe.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class ControllerAdvisor {

    @ExceptionHandler(value = RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleExceptionForGlobal(RuntimeException e, Model model) {
        log.error("[ERROR] - {}", e.getMessage());
        model.addAttribute("msg", e.getMessage());
        return "/error";
    }
}
