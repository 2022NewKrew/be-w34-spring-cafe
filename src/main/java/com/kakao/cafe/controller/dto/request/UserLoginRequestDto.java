package com.kakao.cafe.controller.dto.request;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Builder
@Getter
public class UserLoginRequestDto {

    @NotBlank
    private final String userId;
    @NotBlank
    private final String password;

}
