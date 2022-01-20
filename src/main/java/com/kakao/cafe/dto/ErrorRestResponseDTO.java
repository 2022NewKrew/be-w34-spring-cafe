package com.kakao.cafe.dto;

import lombok.Getter;

@Getter
public class ErrorRestResponseDTO extends RestResponseDTO {
    private final boolean success = false;
    private final String message;

    public ErrorRestResponseDTO(String message) {
        super(false);
        this.message = message;
    }
}
