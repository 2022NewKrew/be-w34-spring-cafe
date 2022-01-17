package com.kakao.cafe.controller.users.dto.request;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UserUpdateRequest {
    private final String userId;
    private final String password;
    private final String name;
    private final String email;

    public UserUpdateRequest(String userId, String password, String name, String email) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
    }
}
