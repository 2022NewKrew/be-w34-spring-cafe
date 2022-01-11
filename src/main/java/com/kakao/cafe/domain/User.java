package com.kakao.cafe.domain;

import com.kakao.cafe.dto.UserCreateRequest;

public class User {
    private Long id;
    private String nickname;
    private String password;
    private String name;
    private String email;

    public User() {}

    public User(UserCreateRequest userCreateRequest) {
        this.nickname = userCreateRequest.getUserId();
        this.password = userCreateRequest.getPassword();
        this.name = userCreateRequest.getName();
        this.email = userCreateRequest.getEmail();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
