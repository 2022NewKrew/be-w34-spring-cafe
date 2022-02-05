package com.kakao.cafe.controller;

import com.kakao.cafe.exception.*;
import com.kakao.cafe.util.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(UserException.class)
    public ResponseEntity<ErrorResponse> handleUserException(UserException e) {
        log.error("UserException : {}", e.getErrorCode().getErrorMessage());
        return ErrorResponse.createResponseEntity(e.getErrorCode());
    }

    @ExceptionHandler(ArticleException.class)
    public ResponseEntity<ErrorResponse> handleArticleException(ArticleException e) {
        log.error("ArticleException : {}", e.getErrorCode().getErrorMessage());
        return ErrorResponse.createResponseEntity(e.getErrorCode());
    }

    @ExceptionHandler(LoginFailedException.class)
    public String handleLoginFailedException(LoginFailedException e) {
        return "redirect:/login/failed";
    }

    @ExceptionHandler(UpdateUserException.class)
    public String handleUpdateUserException(RuntimeException e) {
        return "redirect:/user/{userId}/form";
    }

    @ExceptionHandler({DuplicateUserException.class, NonExistUserException.class, UserAccessException.class})
    public ResponseEntity<ErrorResponse> handleExceptions(CustomException e) {
        return ErrorResponse.createResponseEntity(e.getErrorCode());
    }
}