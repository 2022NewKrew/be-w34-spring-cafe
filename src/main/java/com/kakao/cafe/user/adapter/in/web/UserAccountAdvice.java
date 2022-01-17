package com.kakao.cafe.user.adapter.in.web;

import com.kakao.cafe.user.exception.UserAccountException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class UserAccountAdvice {

    @ExceptionHandler(UserAccountException.class)
    public ResponseEntity<Void> handler(UserAccountException exception) {
        log.error(exception.getErrorMessage());
        return ResponseEntity
                .status(exception.getErrorCode())
                .build();
    }

}
