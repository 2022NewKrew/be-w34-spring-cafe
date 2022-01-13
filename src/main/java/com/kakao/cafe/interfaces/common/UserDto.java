package com.kakao.cafe.interfaces.common;

import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String email;
    private String nickname;
    private String password;
}
