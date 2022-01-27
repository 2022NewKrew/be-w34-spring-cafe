package com.kakao.cafe.user.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SignUpDTO {
    private final String userId;
    private final String password;
    private final String name;
    private final String email;
}
