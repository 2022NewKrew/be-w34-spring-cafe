package com.kakao.cafe.controller;

import com.kakao.cafe.util.exception.KakaoCafeException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionAdvice {
    @ExceptionHandler(KakaoCafeException.class)
    public String handleUserException(KakaoCafeException e, Model model) {
        model.addAttribute("errorMessage", e.getMessage());
        return "error/error";
    }

    @ExceptionHandler(Exception.class)
    public String handleCommonException(Exception e, Model model) {
        e.printStackTrace();
        model.addAttribute("errorMessage", "처리 중에 문제가 발생하였습니다.");
        return "error/error";
    }
}
