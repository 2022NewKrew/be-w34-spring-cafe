package com.kakao.cafe.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ControllerAdvisor {

    @ExceptionHandler(value = RuntimeException.class)
    public String handleExceptionForGlobal(RuntimeException e, Model model) {
        log.error("[ERROR] - {}", e.getMessage());
        model.addAttribute("msg", e.getMessage());
        return "/error";
    }
}
