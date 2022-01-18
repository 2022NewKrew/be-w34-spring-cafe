package com.kakao.cafe.config;

import com.kakao.cafe.exception.SessionUserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SessionUserNotFoundException.class)
    public String handleSessionUserNotFoundException(SessionUserNotFoundException e) {
        return "redirect:/users/login";
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String handleIllegalArgumentException(IllegalArgumentException e) {
        log.info("{}", e.getMessage());
        return "redirect:/"; // TODO: ERROR PAGE에서 에러를 표시할 수 있도록 수정
    }
}
