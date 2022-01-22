package com.kakao.cafe.advice;

import com.kakao.cafe.exception.NotSessionInfo;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class CommonExceptionHandler {
    @ExceptionHandler({IllegalArgumentException.class, NullPointerException.class})
    public ModelAndView handleArithmeticException(Exception e) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("errorMessage", e.getMessage());
        mav.setViewName("error/error");
        return mav;
    }

    @ExceptionHandler(NotSessionInfo.class)
    public ModelAndView NotSessionInfoException(Exception e) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("errorMessage", e.getMessage());
        mav.setViewName("user/login");
        return mav;
    }
}
