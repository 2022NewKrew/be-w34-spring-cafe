package com.kakao.cafe.user.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@RequiredArgsConstructor
@Getter
@ToString
public class UserLoginForm {

    @NotBlank
    private final String username;

    @NotBlank
    private final String password;
}
