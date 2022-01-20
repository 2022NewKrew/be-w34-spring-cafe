package com.kakao.cafe.interfaces.common;

import com.kakao.cafe.application.user.exception.UnauthorizedUserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = IllegalArgumentException.class)
    public ModelAndView handle(IllegalArgumentException e) {
        logger.info("에러 원인: {}", e.getMessage());
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

    @ExceptionHandler(value = UnauthorizedUserException.class)
    public ModelAndView handle(UnauthorizedUserException e) {
        logger.info("권한 예외 발생: {}", e.getMessage());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", e.getMessage());
        modelAndView.setViewName("error");
        return modelAndView;
    }

    @ExceptionHandler(value = Exception.class)
    public ModelAndView handle(Exception e) {
        logger.info("예외 발생: {}", e.getStackTrace());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", "에러 발생");
        modelAndView.setViewName("error");
        return modelAndView;
    }
}
