package com.example.kakaocafe.core.aop;

import com.example.kakaocafe.core.exception.SignUpException;
import com.example.kakaocafe.core.meta.ViewPath;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;
import java.util.NoSuchElementException;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(SignUpException.class)
    public ModelAndView signUpExceptionHandle(HttpServletRequest req) {
        final ModelAndView modelAndView = new ModelAndView(ViewPath.SIGN_UP_FAILED.getPath());

        req.getParameterMap().forEach((key, value) -> modelAndView.addObject(key, value[0]));

        return modelAndView;
    }

    @ExceptionHandler(LoginException.class)
    public ModelAndView loginExceptionHandle() {
        return new ModelAndView(ViewPath.LOGIN_FAILED.getPath());
    }

    @ExceptionHandler(NoSuchElementException.class)
    public String noSuchElementExceptionHandle() {
        return "404 에러지롱";
    }
}
