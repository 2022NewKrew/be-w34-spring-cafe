package com.kakao.cafe.user.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class User {

    private final String username;
    private final String password;
    private final String name;
    private final String email;
    private Long id;

    @Builder
    public User(String username, String password, String name, String email) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
