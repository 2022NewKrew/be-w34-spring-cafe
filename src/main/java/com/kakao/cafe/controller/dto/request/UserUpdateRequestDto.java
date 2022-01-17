package com.kakao.cafe.controller.dto.request;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Builder
public class UserUpdateRequestDto {

    @NotBlank
    private final String name;
    @NotBlank
    private final String password;
    @NotBlank
    @Email
    private final String email;
}
