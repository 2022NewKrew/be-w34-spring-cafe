package com.kakao.cafe.controller.dto.request;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Builder
public class UserSignUpRequestDto {

    @NotBlank
    private final String userId;
    @NotBlank
    private final String password;
    @NotBlank
    private final String name;
    @NotBlank
    @Email
    private final String email;

}
