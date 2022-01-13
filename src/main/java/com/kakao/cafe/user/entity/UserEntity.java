package com.kakao.cafe.user.entity;


import lombok.Builder;

public class UserEntity {
    private Long id;
    private String userId;
    private String password;
    private String name;
    private String email;

    @Builder
    private UserEntity(Long id, String userId, String password, String name, String email) {
        this.id = id;
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void update(String password, String name, String email) {
        this.password = password;
        this.name = name;
        this.email = email;
    }
}
