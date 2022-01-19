package com.kakao.cafe.exception.handler;

import com.kakao.cafe.article.controller.ArticleController;
import com.kakao.cafe.exception.ErrorCode;
import com.kakao.cafe.exception.ErrorResponse;
import com.kakao.cafe.exception.custom.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice(basePackageClasses = ArticleController.class)
public class ArticleExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<ErrorResponse> handleNotFoundException(Exception e) {
        log.error("handleNotFoundException", e);
        final ErrorResponse response = ErrorResponse.of(ErrorCode.NOT_FOUND_ARTICLE_ID);
        return new ResponseEntity<>(response, HttpStatus.valueOf(ErrorCode.NOT_FOUND_ARTICLE_ID.getStatus()));
    }
}
