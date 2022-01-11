package com.kakao.cafe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@Getter
@Setter
public class UserForm {

    @NotEmpty
    private String userId;

    @NotEmpty
    private String password;

    @NotEmpty
    private String name;

    @NotEmpty
    @Email
    private String email;
}
