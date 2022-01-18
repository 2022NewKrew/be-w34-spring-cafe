package com.kakao.cafe.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
public class UserRequest {
    private String userId;
    private String password;
    private String name;
    private String email;
}
