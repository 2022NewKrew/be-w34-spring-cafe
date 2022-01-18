package com.kakao.cafe.dto;

import java.time.LocalDateTime;

public class UserResponseDTO {
    private final Long id;
    private final String userId;
    private final String name;
    private final String email;
    private final LocalDateTime createdAt;

    public static UserResponseDTO of(Long id, String userId, String name, String email, LocalDateTime createdAt) {
        return new UserResponseDTO(id, userId, name, email, createdAt);
    }

    public UserResponseDTO(Long id, String userId, String name, String email, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
