package com.kakao.cafe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UserFormDto {
    private String userId;
    private String password;
    private String name;
    private String email;
}
