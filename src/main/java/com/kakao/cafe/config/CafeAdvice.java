package com.kakao.cafe.config;

import com.kakao.cafe.exception.CafeException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class CafeAdvice {
    @ExceptionHandler(CafeException.class)
    public String handleCafeException() {
        return "error/404";
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public String handleMethodArgumentTypeMismatchException() {
        return "error/404";
    }
}
