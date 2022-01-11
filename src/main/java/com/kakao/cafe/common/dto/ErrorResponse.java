package com.kakao.cafe.common.dto;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;

public class ErrorResponse {

    private final List<String> body;

    private ErrorResponse(List<String> body) {
        this.body = body;
    }

    public static ErrorResponse of(BindingResult bindingResult) {
        List<String> collect = bindingResult.getFieldErrors().stream()
            .map(DefaultMessageSourceResolvable::getDefaultMessage)
            .collect(Collectors.toList());
        return new ErrorResponse(collect);
    }

    public static ErrorResponse of(String message) {
        return new ErrorResponse(List.of(message));
    }

    @Override
    public String toString() {
        return body.toString();
    }
}
