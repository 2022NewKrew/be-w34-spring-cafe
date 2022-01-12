package com.kakao.cafe.dto;

import lombok.Data;

@Data
public class UserDto {
    private String userId;
    private String password;
    private String name;
    private String email;
}
