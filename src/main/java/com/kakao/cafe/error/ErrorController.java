package com.kakao.cafe.error;

import com.kakao.cafe.error.exception.duplication.DuplicationException;
import com.kakao.cafe.error.exception.nonexist.NotFoundedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorController {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundedException.class)
    public String notFounded(NotFoundedException e) {
        return e.getMessage();
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DuplicationException.class)
    public String duplicationFounded(DuplicationException e) {
        return e.getMessage();
    }
}
