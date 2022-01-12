package com.kakao.cafe.config;

import com.kakao.cafe.exception.CafeException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CafeAdvice {
    @ExceptionHandler(CafeException.class)
    public String handleCafeException() {
        return "error/404";
    }
}
