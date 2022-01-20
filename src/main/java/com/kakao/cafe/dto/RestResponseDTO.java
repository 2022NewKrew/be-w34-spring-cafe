package com.kakao.cafe.dto;

import lombok.Getter;

@Getter
public class RestResponseDTO {
    private final boolean success;

    public RestResponseDTO(boolean success) {
        this.success = success;
    }
}
