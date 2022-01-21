package com.kakao.cafe.controller;

import com.kakao.cafe.util.exception.CustomException;
import com.kakao.cafe.util.exception.throwable.InvalidPasswordException;
import com.kakao.cafe.util.exception.throwable.UnauthorizedActionException;
import com.kakao.cafe.util.exception.throwable.UnavailableActionException;
import com.kakao.cafe.util.exception.wrappable.*;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({UserNotFoundException.class, UserDuplicateException.class, UnavailableActionException.class, InvalidPasswordException.class})
    public String userError(HttpServletRequest req, CustomException exception, Model model) {
        model.addAttribute("exception", exception);
        model.addAttribute("detail", exception.getDetail());
        return "error/error";
    }


    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({PostNotFoundException.class, CommentNotFoundException.class})
    public String userNotFound(HttpServletRequest req, CustomException exception, Model model) {
        model.addAttribute("exception", exception);
        model.addAttribute("detail", exception.getDetail());
        return "error/error";
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({LoginFailException.class, UnauthorizedActionException.class})
    public String loginFail(HttpServletRequest req, CustomException exception, Model model) {
        model.addAttribute("exception", exception);
        model.addAttribute("detail", exception.getDetail());
        return "error/error";
    }

}
