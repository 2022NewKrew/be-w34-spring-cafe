package com.kakao.cafe.domain;

import lombok.Builder;

@Builder
public class User {
    private final String userId;
    private final String password;
    private final String name;
    private final String email;

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
