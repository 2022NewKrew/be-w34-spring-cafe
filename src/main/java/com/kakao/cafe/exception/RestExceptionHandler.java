package com.kakao.cafe.exception;

import com.kakao.cafe.web.dto.ErrorDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice("com.kakao.cafe.web.controller.api")
public class RestExceptionHandler {

  private static final Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

  @ExceptionHandler(Exception.class)
  private ResponseEntity<ErrorDTO> handleException(Exception e) {
    ErrorConst error = ErrorConst.findBy(e);
    return ResponseEntity.status(error.getStatus())
        .body(ErrorDTO.of(error));
  }

}
