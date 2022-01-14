package com.kakao.cafe.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
public class UserUpdateRequest {

    @NotNull
    @NotBlank
    private final String passwordCheck;
    @NotNull
    @NotBlank
    private final String newPassword;
    @NotNull
    @NotBlank
    private final String name;
    @NotNull
    @NotBlank
    private final String email;
}
