package com.kakao.cafe.domain;

import java.util.UUID;
import javax.validation.constraints.NotBlank;

public class User {

    private final UUID id;
    @NotBlank
    private final String userId;
    @NotBlank
    private final String password;
    @NotBlank
    private final String name;
    @NotBlank
    private final String email;

    public User(String userId, String password, String name, String email) {
        this.id = UUID.randomUUID();
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return name;
    }
}
