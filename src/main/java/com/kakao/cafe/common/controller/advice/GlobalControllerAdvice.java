package com.kakao.cafe.common.controller.advice;

import java.util.NoSuchElementException;

import lombok.extern.slf4j.Slf4j;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.kakao.cafe.common.exception.IllegalLoginException;
import com.kakao.cafe.common.exception.UserAuthException;

@Slf4j
@ControllerAdvice
public class GlobalControllerAdvice {
    @ExceptionHandler(IllegalLoginException.class)
    public String handleIllegalLoginException(IllegalLoginException exception, Model model) {
        log.error(exception.getMessage());
        model.addAttribute("msg", "아이디 또는 비밀번호가 틀립니다. 다시 로그인 해주세요.");

        return "user/login_failed";
    }

    @ExceptionHandler(UserAuthException.class)
    public String handleUserAuthException(UserAuthException exception, Model model) {
        log.error(exception.getMessage());
        model.addAttribute("msg", "권한이 필요합니다.");

        return "error";
    }

    @ExceptionHandler(NoSuchElementException.class)
    public String NoSuchElementException(NoSuchElementException exception, Model model) {
        log.error(exception.getMessage());
        model.addAttribute("msg", "잘못된 조회입니다.");

        return "error";
    }
}
