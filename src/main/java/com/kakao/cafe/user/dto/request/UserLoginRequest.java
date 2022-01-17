package com.kakao.cafe.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
public class UserLoginRequest {

    @NotNull
    @NotBlank
    private final String userId;
    @NotNull
    @NotBlank
    private final String password;
}
