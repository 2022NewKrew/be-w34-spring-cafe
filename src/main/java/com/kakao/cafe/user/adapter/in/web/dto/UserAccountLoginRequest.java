package com.kakao.cafe.user.adapter.in.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@RequiredArgsConstructor
public class UserAccountLoginRequest {

    @Email
    private final String email;
    @NotNull
    private final String password;

}
