package com.kakao.cafe.user.web;

import com.kakao.cafe.exception.CustomException;
import com.kakao.cafe.exception.InvalidDtoException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice(basePackageClasses = UserController.class)
public class UserControllerExceptionHandler {

    @ExceptionHandler(InvalidDtoException.class) // @Valid 검증 실패 시
    protected ResponseEntity<String> handleParameterException(InvalidDtoException e) {
        log.error("handleInvalidParameterException", e);
        return new ResponseEntity<>(e.getMessage(),
            HttpStatus.resolve(e.getErrorCode().getStatus()));
    }

    @ExceptionHandler(CustomException.class) // CustomException 발생시
    protected ResponseEntity<String> handleCustomException(CustomException e) {
        log.error("handleCustomException", e);
        return new ResponseEntity<>(e.getErrorCode().getMessage(),
            HttpStatus.resolve(e.getErrorCode().getStatus()));
    }

    @ExceptionHandler(Exception.class) // 모든 예외 핸들링
    protected ResponseEntity<String> handleException(Exception e) {
        log.error("handleAllException", e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
