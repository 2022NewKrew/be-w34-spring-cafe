package com.kakao.cafe.app.controller;

import com.kakao.cafe.domain.exception.DuplicateUserIdException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(DuplicateUserIdException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleDuplicateUserIdException() {
        return "error/duplicateUserId";
    }
}
