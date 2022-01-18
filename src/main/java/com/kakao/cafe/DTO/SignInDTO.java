package com.kakao.cafe.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SignInDTO {
    private final String userId;
    private final String password;
    private final String name;
    private final String email;
}
