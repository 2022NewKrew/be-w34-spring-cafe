package com.kakao.cafe.exception;

import com.sun.jdi.request.DuplicateRequestException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @ExceptionHandler(UnauthenticatedException.class)
    public String unauthenticatedError(UnauthenticatedException e, RedirectAttributes attributes) {
        log.error("{}", e.getMessage());

        Error error = new Error();
        error.setErrorMessage(e.getMessage());
        attributes.addFlashAttribute("error", error);
        return "redirect:/users/login";
    }

    @Data
    static class Error {
        private String errorMessage;
    }
}
