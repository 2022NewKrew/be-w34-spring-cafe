package com.kakao.cafe.error.handler;

import com.kakao.cafe.error.exception.ArticleNotFoundException;
import com.kakao.cafe.error.exception.AuthInvalidPasswordException;
import com.kakao.cafe.error.exception.AuthInvalidUidException;
import com.kakao.cafe.error.exception.UserAlreadyExistsException;
import com.kakao.cafe.error.exception.UserInvalidAuthInfoException;
import com.kakao.cafe.error.exception.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(UserNotFoundException.class)
    public String handleUserNotFoundException(UserNotFoundException e) {
        logger.error(e.getMessage());
        return "/user/profile";
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public String handleUserAlreadyExistsException(UserAlreadyExistsException e) {
        logger.error(e.getMessage());
        return "redirect:/users/form-failed";
    }

    @ExceptionHandler(ArticleNotFoundException.class)
    public String handleArticleNotFoundException(ArticleNotFoundException e) {
        logger.error(e.getMessage());
        return "/qna/show";
    }

    @ExceptionHandler({AuthInvalidPasswordException.class, AuthInvalidUidException.class})
    public String handleAuthInvalidException(RuntimeException e) {
        logger.error(e.getMessage());
        return "redirect:/login-failed";
    }

    @ExceptionHandler(UserInvalidAuthInfoException.class)
    public String handleUserInvalidAuthInfoException(UserInvalidAuthInfoException e) {
        logger.error(e.getMessage());
        return "redirect:/users";
    }
}
