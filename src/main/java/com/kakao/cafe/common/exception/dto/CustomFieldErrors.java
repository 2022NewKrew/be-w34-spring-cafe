package com.kakao.cafe.common.exception.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.validation.FieldError;

public class CustomFieldErrors {

    private final List<CustomFieldError> customFieldErrors;

    public CustomFieldErrors(List<CustomFieldError> customFieldErrors) {
        this.customFieldErrors = new ArrayList<>(customFieldErrors);
    }

    public static CustomFieldErrors of() {
        return new CustomFieldErrors(new ArrayList<>());
    }

    public static CustomFieldErrors of(List<FieldError> fieldErrors) {
        return new CustomFieldErrors(fieldErrors.stream()
            .map(CustomFieldError::of)
            .collect(Collectors.toList()));
    }
}
