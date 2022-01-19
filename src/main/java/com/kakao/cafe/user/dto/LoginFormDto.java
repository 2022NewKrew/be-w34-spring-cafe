package com.kakao.cafe.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginFormDto {
    private String userId;
    private String password;
}
