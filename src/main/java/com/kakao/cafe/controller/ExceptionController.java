package com.kakao.cafe.controller;

import com.kakao.cafe.util.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@ControllerAdvice
public class ExceptionController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({UserNotFoundException.class, UserDuplicateException.class})
    public String userError(HttpServletRequest req, CustomException e, Model model) {
        model.addAttribute("e", e);
        model.addAttribute("detail", e.getDetail());
        return "error/error";
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({InvalidPasswordException.class})
    public String passwordError(HttpServletRequest req, RuntimeException e, Model model) {
        model.addAttribute("e", e);
        model.addAttribute("detail", "");
        return "error/error";
    }


    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({PostNotFoundException.class})
    public String userNotFound(HttpServletRequest req, CustomException e, Model model) {
        model.addAttribute("e", e);
        model.addAttribute("detail", e.getDetail());
        return "error/error";
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({LoginFailException.class, UnauthorizedAction.class})
    public String loginFail(HttpServletRequest req, CustomException exception, Model model, HttpSession httpSession) {
        model.addAttribute("exception", exception);
        model.addAttribute("detail", exception.getDetail());
        return "error/error";
    }

}
