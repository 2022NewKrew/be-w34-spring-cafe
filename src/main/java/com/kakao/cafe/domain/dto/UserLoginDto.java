package com.kakao.cafe.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;


@AllArgsConstructor
@Getter
public class UserLoginDto {
    @NotBlank
    private String userId;

    @NotBlank
    private String password;
}
