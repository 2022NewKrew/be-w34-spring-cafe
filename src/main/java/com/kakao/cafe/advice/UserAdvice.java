package com.kakao.cafe.advice;

import com.kakao.cafe.exception.UnauthenticatedException;
import com.kakao.cafe.exception.UnauthorizedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Slf4j
public class UserAdvice {

    @ExceptionHandler(UnauthenticatedException.class)
    public String unauthenticatedException(HttpServletRequest request, Model model) {
        log.info("start unauthenticatedException()");
        model.addAttribute("referer", request.getHeader("referer"));
        model.addAttribute("message", "로그인이 필요합니다.");
        return "/error";
    }

    @ExceptionHandler(UnauthorizedException.class)
    public String unauthorizedException(HttpServletRequest request, Model model) {
        log.info("start unauthorizedException()");
        model.addAttribute("referer", request.getHeader("referer"));
        model.addAttribute("message", "권한이 없습니다.");
        return "/error";
    }

}
