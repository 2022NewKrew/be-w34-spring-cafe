package com.kakao.cafe.exception.handler;

import com.kakao.cafe.account.controller.AccountController;
import com.kakao.cafe.exception.ErrorCode;
import com.kakao.cafe.exception.ErrorResponse;
import com.kakao.cafe.exception.custom.DuplicateException;
import com.kakao.cafe.exception.custom.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice(basePackageClasses = AccountController.class)
public class AccountExceptionHandler {

    @ExceptionHandler(DuplicateException.class)
    protected ResponseEntity<ErrorResponse> handleDuplicateException(Exception e) {
        log.error("handleDuplicateException", e);
        final ErrorResponse response = ErrorResponse.of(ErrorCode.DUPLICATED_USER_ID);
        return new ResponseEntity<>(response, HttpStatus.valueOf(ErrorCode.DUPLICATED_USER_ID.getStatus()));
    }

    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<ErrorResponse> handleNotFoundException(Exception e) {
        log.error("handleNotFoundException", e);
        final ErrorResponse response = ErrorResponse.of(ErrorCode.NOT_FOUND_USER_ID);
        return new ResponseEntity<>(response, HttpStatus.valueOf(ErrorCode.NOT_FOUND_USER_ID.getStatus()));
    }

    @ExceptionHandler(DuplicateKeyException.class)
    protected ResponseEntity<ErrorResponse> handleDuplicateKeyException(Exception e) {
        log.error("handleDuplicateKeyException", e);
        final ErrorResponse response = ErrorResponse.of(ErrorCode.DUPLICATED_USER_ID);
        return new ResponseEntity<>(response, HttpStatus.valueOf(ErrorCode.DUPLICATED_USER_ID.getStatus()));
    }
}
