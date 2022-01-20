package com.kakao.cafe.config;

import com.kakao.cafe.exception.SessionUserNotFoundException;
import com.kakao.cafe.exception.UpdateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SessionUserNotFoundException.class)
    public String handleSessionUserNotFoundException(SessionUserNotFoundException e) {
        return "redirect:/users/login";
    }

    @ExceptionHandler(UpdateException.class)
    public String handleIllegalArgumentException(UpdateException e, Model model) {
        log.info("{}", e.getMessage());
        model.addAttribute("message", e.getMessage());
        return "/error"; // TODO: 상세 로직 추가..
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception e, Model model) {
        log.info("{}", e.getMessage());
        model.addAttribute("message", e.getMessage());
        return "/error";
    }
}
