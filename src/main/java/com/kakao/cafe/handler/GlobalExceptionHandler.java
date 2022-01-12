package com.kakao.cafe.handler;

import com.kakao.cafe.exceptions.InvalidUser;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidUser.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String invalidUser() {
        return "error/error";
    }
}
