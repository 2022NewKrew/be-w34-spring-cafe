package com.kakao.cafe.interfaces.common;

import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = IllegalArgumentException.class)
    public ModelAndView handle(IllegalArgumentException e) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", e.getMessage());
        modelAndView.setViewName("error");
        return modelAndView;
    }

    @ExceptionHandler(value = BindException.class)
    public ModelAndView handle(BindException e) {
        List<String> errorMessages = e.getBindingResult().getAllErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.toList());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", "올바르지 않은 입력 값(" + String.join(", ", errorMessages) + ")");
        modelAndView.setViewName("error");
        return modelAndView;
    }

    @ExceptionHandler(value = Exception.class)
    public ModelAndView handle(Exception e) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", "에러 발생");
        modelAndView.setViewName("error");
        return modelAndView;
    }
}
