package com.kakao.cafe.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@AllArgsConstructor
@ToString
public class LoginRequestDTO {

    @Email
    @NotBlank
    private String email;

    @NotEmpty
    private String password;
}
