package com.kakao.cafe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
public class UserLoginDto {
    @NotBlank
    private String userId;
    @NotBlank
    private String password;
}
