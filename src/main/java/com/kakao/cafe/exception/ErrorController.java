package com.kakao.cafe.exception;

import com.sun.jdi.request.DuplicateRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
@Slf4j
public class ErrorController {

    @ExceptionHandler(NoSuchElementException.class)
    public String noSuchElementError(NoSuchElementException e, Model model) {
        log.error("{}", e.getMessage());
        model.addAttribute("error", e.getMessage());
        return "error/error-page";
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String illegalFormatError(IllegalArgumentException e, Model model) {
        log.error("{}", e.getMessage());
        model.addAttribute("error", e.getMessage());
        return "error/error-page";
    }

    @ExceptionHandler(DuplicateRequestException.class)
    public String duplicatedUserIdError(DuplicateRequestException e, Model model) {
        log.error("{}", e.getMessage());
        model.addAttribute("error", e.getMessage());
        return "error/error-page";
    }

    @ExceptionHandler(UnauthorizedException.class)
    public String unauthorizedError(UnauthorizedException e, Model model) {
        log.error("{}", e.getMessage());
        model.addAttribute("error", e.getMessage());
        return "error/error-page";
    }
}
