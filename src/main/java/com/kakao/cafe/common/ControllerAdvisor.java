package com.kakao.cafe.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Arrays;

@ControllerAdvice
@Slf4j
public class ControllerAdvisor {

    @ExceptionHandler(value = RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleCustomException(RuntimeException e, Model model) {
        log.error("[ERROR] - {}", e.getMessage());
        model.addAttribute("msg", e.getMessage());
        return "/error";
    }

    @ExceptionHandler(value = BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleValidationException(BindException e, Model model) {
        String message = getResultMessage(e);

        log.error("[ERROR] - {}", message);
        model.addAttribute("msg", message);

        return "/error";
    }

    private String getResultMessage(BindException e) {
        StringBuilder resultMessageBuilder = new StringBuilder("\n");

        e.getFieldErrors().forEach(fe -> {
            resultMessageBuilder.append(fe.getField())
                                .append(" : ")
                                .append(fe.getDefaultMessage()).append("\n");
        });

        return resultMessageBuilder.toString();
    }
}
