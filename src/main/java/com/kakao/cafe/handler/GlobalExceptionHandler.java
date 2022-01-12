package com.kakao.cafe.handler;

import com.kakao.cafe.exception.DuplicateUserException;
import com.kakao.cafe.exception.NoSuchArticleException;
import com.kakao.cafe.exception.NoSuchUserException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({DuplicateUserException.class, DuplicateKeyException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public String userDuplicated() {
        return "error/userduplicated";
    }

    @ExceptionHandler(NoSuchUserException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String userNotFound() {
        return "error/nosuchuser";
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String illegalArgument() {
        return "error/illegalargument";
    }

    @ExceptionHandler(NoSuchArticleException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String articleNotFound() {
        return "error/nosucharticle";
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String validationFailed() {
        return "error/invalidinput";
    }
}
