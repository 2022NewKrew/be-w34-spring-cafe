package com.kakao.cafe.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserDto {
    private String userId;
    private String name;
    private String email;
}
