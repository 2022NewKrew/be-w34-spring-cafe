package com.kakao.cafe.user.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@RequiredArgsConstructor
@Getter
@ToString
public class UserEditForm {

    @Email
    private final String email;

    @NotBlank
    private final String displayName;
}
