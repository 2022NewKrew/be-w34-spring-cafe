package com.kakao.cafe.dto;

import lombok.Getter;

@Getter
public class UserProfile {
    private String name;
    private String email;

    public UserProfile(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
