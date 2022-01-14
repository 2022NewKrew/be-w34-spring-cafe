package com.kakao.cafe.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
public class UserCreateRequest {

    @NotNull
    @NotBlank
    private final String userId;
    @NotNull
    @NotBlank
    private final String password;
    @NotNull
    @NotBlank
    private final String name;
    @NotNull
    @NotBlank
    private final String email;
}
