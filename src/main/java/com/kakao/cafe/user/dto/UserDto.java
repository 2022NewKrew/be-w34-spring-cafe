package com.kakao.cafe.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private Long id;
    private String userId;
    private String name;
    private String email;
}
