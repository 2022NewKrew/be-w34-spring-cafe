package com.kakao.cafe.dto;

import java.time.LocalDateTime;

public class UserResponseDto {
    private Long id;
    private String userId;
    private String name;
    private String email;
    private LocalDateTime createdAt;

    public static UserResponseDto of(Long id, String userId, String name, String email, LocalDateTime createdAt) {
        return new UserResponseDto(id, userId, name, email, createdAt);
    }

    public UserResponseDto(Long id, String userId, String name, String email, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
