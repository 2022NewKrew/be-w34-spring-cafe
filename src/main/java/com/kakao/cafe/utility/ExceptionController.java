package com.kakao.cafe.utility;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(UserException.class)
    protected ResponseEntity<ErrorResponse> handleCustomException(UserException e) {
        log.error("handleDataException throw Exception : {}", e.getErrorCode());
        return ErrorResponse.createResponseEntity(e.getErrorCode());
    }
}
