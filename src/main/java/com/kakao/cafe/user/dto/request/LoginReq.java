package com.kakao.cafe.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Getter @Setter
public class LoginReq {

    @NotBlank
    private final String userId;
    @NotBlank
    private final String password;
}
