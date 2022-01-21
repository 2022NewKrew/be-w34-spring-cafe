package com.kakao.cafe.handler;

import com.kakao.cafe.exception.DuplicateUserException;
import com.kakao.cafe.exception.LoginFailedException;
import com.kakao.cafe.exception.LoginRequiredException;
import com.kakao.cafe.exception.NoSuchArticleException;
import com.kakao.cafe.exception.NoSuchUserException;
import com.kakao.cafe.exception.UnauthenticatedArticleAccessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final Logger log;

    public GlobalExceptionHandler() {
        this.log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    }

    private void logHandlingException(Exception e) {
        log.info("Exception Handled: class={}, message={}",
                e.getClass().getName(),
                e.getMessage());
    }

    @ExceptionHandler(DuplicateUserException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String userDuplicated(Exception exception) {
        logHandlingException(exception);
        return "error/userduplicated";
    }

    @ExceptionHandler(NoSuchUserException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String userNotFound(Exception exception) {
        logHandlingException(exception);
        return "error/no-such-user";
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String illegalArgument(Exception exception) {
        logHandlingException(exception);
        return "error/illegal-argument";
    }

    @ExceptionHandler(NoSuchArticleException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String articleNotFound(Exception exception) {
        logHandlingException(exception);
        return "error/no-such-article";
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String validationFailed(Exception exception) {
        logHandlingException(exception);
        return "error/invalid-input";
    }

    @ExceptionHandler(LoginFailedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String loginFailed(Exception exception) {
        logHandlingException(exception);
        return "error/login-failed";
    }

    @ExceptionHandler(LoginRequiredException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String loginRequired(Exception exception) {
        logHandlingException(exception);
        return "auth/login";
    }

    @ExceptionHandler(UnauthenticatedArticleAccessException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String unauthenticatedArticleAccess(Exception exception) {
        logHandlingException(exception);
        return "error/unauthenticated-article-access";
    }
}
