package com.kakao.cafe.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class ExceptionAdvice {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String exception(Exception e) {
        log.info("에러 : {}", e.getClass().getName());
        return e.getMessage();
    }

    @ExceptionHandler(InputDataException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String inputError(Model model, Exception e) {
        log.info("에러 : {}", e.getMessage());
        model.addAttribute("msg", e.getMessage());
        return "./error/alert";
    }

    @ExceptionHandler(LoginFailedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String loginError(LoginFailedException e) {
        log.info("에러 : {}", e.getMessage());
        return "./user/login_failed";
    }

    @ExceptionHandler(NullSessionException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String nullSessionError(Model model, Exception e) {
        log.info("에러 : {}", e.getMessage());
        model.addAttribute("msg", e.getMessage());
        return "./error/alert";
    }

    @ExceptionHandler(UserMismatchException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String userMismatchError(Model model, Exception e) {
        log.info("에러 : {}", e.getMessage());
        model.addAttribute("msg", e.getMessage());
        return "./error/alert";
    }
}
