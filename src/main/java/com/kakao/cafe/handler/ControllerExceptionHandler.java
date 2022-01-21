package com.kakao.cafe.handler;

import com.kakao.cafe.Exception.AuthFailException;
import com.kakao.cafe.Exception.NotAuthorizedException;
import com.kakao.cafe.Exception.NotLoginException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(AuthFailException.class)
    protected String handleAuthFailException(AuthFailException e, Model model) {
        model.addAttribute("exception", e);
        return "errors/400";
    }

    @ExceptionHandler(NotAuthorizedException.class)
    protected String handlerNotAuthorizedException(NotAuthorizedException e, Model model) {
        model.addAttribute("exception", e);
        return "errors/notAuthorized";
    }

    @ExceptionHandler(NotLoginException.class)
    protected String handlerNotLoginException(NotLoginException e) {
        return "redirect:/login";
    }
}
