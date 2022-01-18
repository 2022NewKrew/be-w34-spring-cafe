package com.kakao.cafe.infra.dao;

import java.time.LocalDateTime;

public class UserCreateCommand {
    private Long id;
    private final LocalDateTime createdTime;
    private final String nickname;
    private final String email;
    private final String name;
    private final String password;

    public UserCreateCommand(LocalDateTime createdTime, String nickname, String email, String name, String password) {
        this.createdTime = createdTime;
        this.nickname = nickname;
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public UserCreateCommand(Long id, LocalDateTime createdTime, String nickname, String email, String name, String password) {
        this.id = id;
        this.createdTime = createdTime;
        this.nickname = nickname;
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public String getNickname() {
        return nickname;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
