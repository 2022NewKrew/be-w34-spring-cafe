package com.kakao.cafe.exception;

import com.sun.jdi.request.DuplicateRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
@Slf4j
public class ErrorController {

    @ExceptionHandler(NoSuchElementException.class)
    //@ResponseStatus(value = HttpStatus.NOT_FOUND)
    public String noSuchElementError(NoSuchElementException e) {
        log.error("{}", e.getMessage());
        return e.getMessage();
        //return "redirect:/";
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String illegalFormatError(IllegalArgumentException e) {
        log.error("{}", e.getMessage());
        return e.getMessage();
        //return "redirect:/";
    }

    @ExceptionHandler(DuplicateRequestException.class)
    public String duplicatedUserIdError(DuplicateRequestException e) {
        log.error("{}", e.getMessage());
        return e.getMessage();
        //return "redirect:/";
    }

    @ExceptionHandler(IllegalAccessException.class)
    public String wrongPasswordError(IllegalArgumentException e) {
        log.error("{}", e.getMessage());
        return e.getMessage();
    }
}
