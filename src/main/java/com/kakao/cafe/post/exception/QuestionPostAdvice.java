package com.kakao.cafe.post.exception;

import com.kakao.cafe.post.exception.QuestionPostException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class QuestionPostAdvice {

    @ExceptionHandler(QuestionPostException.class)
    public ResponseEntity<Void> handler(QuestionPostException exception) {
        log.error(exception.getErrorMessage());
        return ResponseEntity
                .status(exception.getErrorCode())
                .build();
    }
}
