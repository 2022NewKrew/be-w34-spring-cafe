package com.kakao.cafe.global.error;

import com.kakao.cafe.global.error.exception.LoginException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // /error 페이지에 exception message 출력
    @ExceptionHandler(Exception.class)
    public ModelAndView defaultHandleError(HttpServletRequest req, Exception ex) {
        logger.error("Request: {} raised {}", req.getRequestURL(), ex.getClass());

        ex.printStackTrace();

        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("message", ex.getMessage());
        return modelAndView;
    }

    // login 관련 exception
    @ExceptionHandler(LoginException.class)
    public ModelAndView loginFailHandle(HttpServletRequest req, LoginException ex) {
        logger.error("Login Request Failed : caused {}", ex.getCause().getMessage());

        ModelAndView modelAndView = new ModelAndView("user/login_failed");
        modelAndView.addObject("message", ex.getCause().getMessage());
        return modelAndView;
    }
}
