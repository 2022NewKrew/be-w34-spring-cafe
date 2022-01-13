package com.kakao.cafe.controller;

import com.kakao.cafe.util.exception.InvalidPasswordException;
import com.kakao.cafe.util.exception.PostNotFoundException;
import com.kakao.cafe.util.exception.UserDuplicateException;
import com.kakao.cafe.util.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({UserNotFoundException.class, UserDuplicateException.class, InvalidPasswordException.class})
    public String userError(HttpServletRequest req, RuntimeException e, Model model) {
        model.addAttribute("e", e);
        return "error/error";
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({PostNotFoundException.class})
    public String userNotFound(HttpServletRequest req, RuntimeException e, Model model) {
        model.addAttribute("e", e);
        return "error/error";
    }

}
