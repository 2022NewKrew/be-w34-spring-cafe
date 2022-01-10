package com.kakao.cafe.dto;

import com.kakao.cafe.domain.User;

import java.time.LocalDate;

public class UserDto {
    private int id;
    private String userId;
    private String email;
    private String name;
    private LocalDate createdAt;

    public UserDto() {
    }

    public UserDto(int id, String userId, String email, String name, LocalDate createdAt) {
        this.id = id;
        this.userId = userId;
        this.email = email;
        this.name = name;
        this.createdAt = createdAt;
    }

    public static UserDto of(User user) {
        return new UserDto(user.getId(), user.getUserId(), user.getEmail(), user.getName(), user.getCreatedAt());
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }
}
