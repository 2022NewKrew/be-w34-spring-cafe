package com.kakao.cafe.controller;

import com.kakao.cafe.util.exception.KakaoCafeException;
import com.kakao.cafe.util.exception.NotMyCommentException;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionAdvice {
    @ExceptionHandler(NotMyCommentException.class)
    @ResponseBody
    public ResponseEntity<String> commentExceptionHandler(NotMyCommentException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

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