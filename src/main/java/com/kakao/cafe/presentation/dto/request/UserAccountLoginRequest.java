package com.kakao.cafe.presentation.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@RequiredArgsConstructor
public class UserAccountLoginRequest {

    @Email
    private final String email;
    @NotNull
    private final String password;

}
