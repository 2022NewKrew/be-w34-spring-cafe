package com.kakao.cafe.advice;

import com.kakao.cafe.util.exception.UserException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionAdvice {
    @ExceptionHandler(UserException.class)
    public String handleUserException(UserException e, Model model) {
        model.addAttribute("errorMessage", e.getMessage());
        return "error/error";
    }

    @ExceptionHandler(Exception.class)
    public String handleCommonException(Model model) {
        model.addAttribute("errorMessage", "처리 중에 문제가 발생하였습니다.");
        return "error/error";
    }
}
