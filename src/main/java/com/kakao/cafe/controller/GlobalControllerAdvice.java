package com.kakao.cafe.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class GlobalControllerAdvice {

    public static final String ERROR_MSG = "error";
    public static final String ERROR_STATUS = "status";
    public static final String REDIRECT_ERROR_NO_PAGE = "redirect:/error/no-page";
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalControllerAdvice.class);

    @ExceptionHandler(IllegalArgumentException.class)
    public String illegalArgumentException(IllegalArgumentException e, RedirectAttributes re) {
        LOGGER.warn(e.getMessage());
        re.addFlashAttribute(ERROR_MSG, e.getMessage());
        return REDIRECT_ERROR_NO_PAGE;
    }

    @ExceptionHandler(ResponseStatusException.class)
    public String responseStatusException(ResponseStatusException e, RedirectAttributes re) {
        LOGGER.warn(e.getMessage());
        re.addFlashAttribute(ERROR_STATUS, e.getStatus());
        re.addFlashAttribute(ERROR_MSG, e.getReason());
        return REDIRECT_ERROR_NO_PAGE;
    }
}
