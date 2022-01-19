package com.kakao.cafe.exception;

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
        return "/user/login_failed";
    }
}
