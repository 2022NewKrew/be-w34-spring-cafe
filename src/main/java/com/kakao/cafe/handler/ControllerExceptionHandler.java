package com.kakao.cafe.handler;

import com.kakao.cafe.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler({
            UserNotFoundException.class,
            ArticleNotFoundException.class
    })
    protected ModelAndView handleNotFoundException(RuntimeException e) {
        ModelAndView view = new ModelAndView("error");
        view.addObject("errorMessage", e.getMessage());
        view.setStatus(HttpStatus.NOT_FOUND);

        return view;
    }

    @ExceptionHandler({
            InvalidPasswordException.class,
            IllegalArgumentException.class,
            IllegalStateException.class
    })
    protected  ModelAndView handleInvalidPasswordException(RuntimeException e) {
        ModelAndView view = new ModelAndView("error");
        view.addObject("errorMessage", e.getMessage());
        view.setStatus(HttpStatus.BAD_REQUEST);

        return view;
    }

    @ExceptionHandler(UnauthorizedAccessException.class)
    protected  ModelAndView handleUnauthorizedException(UnauthorizedAccessException e) {
        ModelAndView view = new ModelAndView("error");
        view.addObject("errorMessage", e.getMessage());
        view.setStatus(HttpStatus.UNAUTHORIZED);

        return view;
    }

    @ExceptionHandler(Exception.class)
    protected ModelAndView handle(Exception e) {
        ModelAndView view = new ModelAndView("error");
        view.addObject("errorMessage", "예상치 못한 에러");
        view.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);

        return view;
    }
}
