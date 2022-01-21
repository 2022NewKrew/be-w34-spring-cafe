package com.kakao.cafe.app.advice;

import com.kakao.cafe.domain.exception.CannotAuthenticateException;
import com.kakao.cafe.domain.exception.DuplicateUserIdException;
import com.kakao.cafe.domain.exception.NoSuchObjectException;
import com.kakao.cafe.domain.exception.NoSuchUserException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(DuplicateUserIdException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleDuplicateUserIdException() {
        return "error/duplicateUserId";
    }

    @ExceptionHandler(CannotAuthenticateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleCannotAuthenticateException() {
        return "error/cannotAuthenticate";
    }

    @ExceptionHandler(NoSuchUserException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String handleNoSuchUserException() {
        return "error/noSuchUser";
    }

    @ExceptionHandler(NoSuchObjectException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNoSuchObjectException() {
        return "error/404";
    }

    @ExceptionHandler(NumberFormatException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNumberFormatException() {
        return "error/404";
    }
}
