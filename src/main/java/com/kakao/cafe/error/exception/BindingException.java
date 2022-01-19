package com.kakao.cafe.error.exception;

import java.util.stream.Collectors;
import org.springframework.validation.BindingResult;

public class BindingException extends RuntimeException {

    private static final String MESSAGE_FORMAT = "Binding ERROR [Caused Field : %s | Message : %s]";

    public BindingException(BindingResult bindingResult) {
        super(bindingResult.getFieldErrors()
            .stream()
            .map(fieldError -> String.format(MESSAGE_FORMAT,
                fieldError.getField(),
                fieldError.getDefaultMessage()))
            .collect(Collectors.joining(System.lineSeparator())));
    }
}
