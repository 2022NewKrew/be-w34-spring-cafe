package com.kakao.cafe.advice;

import com.kakao.cafe.exception.*;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CafeControllerExceptionHandler {

    public static final String INCORRECT_PASSWORD_ERROR = "/error/incorrect_password_error";
    public static final String NOT_LOGIN_ERROR = "/error/not_login_error";
    public static final String USER_NOT_EXIST_ERROR = "/error/user_not_exist_error";
    public static final String INCORRECT_USER_ERROR = "/error/incorrect_user_error";
    public static final String DUPLICATE_USER_ERROR = "/error/duplicate_user_error";
    public static final String OTHER_WRITER_REPLY_EXIST_ERROR = "/error/other_writer_reply_exist_error";

    @ExceptionHandler(IncorrectPasswordException.class)
    public String incorrectPasswordError(IncorrectPasswordException e) {
        return INCORRECT_PASSWORD_ERROR;
    }

    @ExceptionHandler(NotLoginException.class)
    public String notLoginError(NotLoginException e) {
        return NOT_LOGIN_ERROR;
    }

    @ExceptionHandler(UserNotExistException.class)
    public String userNotExistError(UserNotExistException e){
        return USER_NOT_EXIST_ERROR;
    }

    @ExceptionHandler(IncorrectUserException.class)
    public String incorrectUserError(IncorrectUserException e) {
        return INCORRECT_USER_ERROR;
    }

    @ExceptionHandler(DuplicateUserException.class)
    public String duplicateUserError(DuplicateUserException e) {
        return DUPLICATE_USER_ERROR;
    }

    @ExceptionHandler(OtherWriterReplyExistException.class)
    public String otherWriterReplyExistError(OtherWriterReplyExistException e) { return OTHER_WRITER_REPLY_EXIST_ERROR; }

}
