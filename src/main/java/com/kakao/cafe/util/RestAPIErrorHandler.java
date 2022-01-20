package com.kakao.cafe.util;

import com.kakao.cafe.controller.ArticleRestController;
import com.kakao.cafe.exception.NoChangeException;
import com.kakao.cafe.exception.NoModifyPermissionException;
import com.kakao.cafe.exception.OtherUserReplyExistException;
import com.kakao.cafe.exception.WrongPasswordException;
import org.springframework.ui.Model;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = ArticleRestController.class)
public class RestAPIErrorHandler {
    @ExceptionHandler(Exception.class)
    public String handleException(Exception exception) {
        exception.printStackTrace();

        return Constants.DEFAULT_ERROR_MESSAGE;
    }


    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public String handleBindException(HttpRequestMethodNotSupportedException exception) {

        return Constants.PAGE_NOT_FOUND_ERROR_MESSAGE;
    }

    @ExceptionHandler({NoChangeException.class, NoModifyPermissionException.class, OtherUserReplyExistException.class, WrongPasswordException.class})
    public String handleControllableExceptions(Exception exception, Model model) {

        return exception.getMessage();
    }
}
