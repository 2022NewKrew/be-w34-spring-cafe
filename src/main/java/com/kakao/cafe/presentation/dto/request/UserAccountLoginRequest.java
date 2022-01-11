package com.kakao.cafe.presentation.dto.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
public class UserAccountLoginRequest {

    @Email
    private final String email;
    @NotNull
    private final String password;

}
