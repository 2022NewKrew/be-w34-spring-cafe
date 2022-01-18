package com.kakao.cafe.aop;

import com.kakao.cafe.exception.CustomEmptyDataAccessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DefaultControllerAdvice {

    private final Logger logger = LoggerFactory.getLogger(DefaultControllerAdvice.class);

    @ExceptionHandler(CustomEmptyDataAccessException.class)
    public ResponseEntity<String> handleEmptyDataAccessException(CustomEmptyDataAccessException e) {
        logger.info("[INFO] 없는 데이터 접근", e.getCause());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}
