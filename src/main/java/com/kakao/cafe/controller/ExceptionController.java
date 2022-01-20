package com.kakao.cafe.controller;

import com.kakao.cafe.exception.InvalidSessionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @ExceptionHandler
    public ResponseEntity<Object> BadRequestException(final RuntimeException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

}
