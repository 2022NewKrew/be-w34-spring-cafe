package com.kakao.cafe.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Getter @Setter
public class UserUpdateReq {

    @NotBlank
    private final String newPassword;
    @NotBlank
    private final String name;
    @Email
    private final String email;

}
