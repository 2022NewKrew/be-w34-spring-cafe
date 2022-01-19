package com.kakao.cafe.session.controller;

import com.kakao.cafe.session.exception.SessionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class SessionExceptionController {
    private static final Logger logger = LoggerFactory.getLogger(SessionExceptionController.class);

    @ExceptionHandler(SessionException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String handle(SessionException e, Model model) {
        logger.warn(e.getMessage());
        model.addAttribute("errorMsg", "잘못된 사용자 정보를 입력했습니다.");
        return "error";
    }
}
