package com.kakao.cafe.home.dto;

import lombok.Getter;

@Getter
public class SessionUser {
    private String userId;
    private String name;
    private String email;

    public SessionUser(String userId, String name, String email) {
        this.userId = userId;
        this.name = name;
        this.email = email;
    }
}
