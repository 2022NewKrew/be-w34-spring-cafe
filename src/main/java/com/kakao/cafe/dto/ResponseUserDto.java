package com.kakao.cafe.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseUserDto {
    private long id;
    private String userId;
    private String password;
    private String name;
    private String email;
}
