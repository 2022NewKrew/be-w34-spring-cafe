package com.kakao.cafe.controller;

import com.kakao.cafe.exception.ForbiddenException;
import com.kakao.cafe.exception.NotFoundException;
import com.kakao.cafe.exception.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionController {
    private final Logger logger = LoggerFactory.getLogger(ExceptionController.class);

    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFoundException(HttpServletRequest req, NotFoundException e) {
        logger.error("Request " + req.getRequestURL() + " raised " + e);
        return createExceptionView(req.getRequestURL(), e, HttpStatus.NOT_FOUND);
    }

    private ModelAndView createExceptionView(StringBuffer url, Exception e, HttpStatus status) {
        ModelAndView view = new ModelAndView();
        view.addObject("url", url);
        view.addObject("exceptionClass", e.getClass());
        view.addObject("exceptionMessage", e.getMessage());
        view.setStatus(status);
        view.setViewName("error");
        return view;
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ModelAndView handleUnauthorizedException(UnauthorizedException e) {
        return new ModelAndView("user/loginForm");
    }

    @ExceptionHandler(ForbiddenException.class)
    public ModelAndView handleForbiddenException(HttpServletRequest req, ForbiddenException e) {
        logger.error("Request " + req.getRequestURL() + " raised " + e);
        return createExceptionView(req.getRequestURL(), e, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleError(HttpServletRequest req, Exception e) {
        e.printStackTrace();
        logger.error("Request " + req.getRequestURL() + " raised " + e);
        return createExceptionView(req.getRequestURL(), e, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
