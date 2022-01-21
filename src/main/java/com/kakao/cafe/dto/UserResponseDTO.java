package com.kakao.cafe.dto;

import lombok.Builder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

public class UserResponseDTO {
    @NotBlank
    private final Long id;
    @NotBlank
    private final String userId;
    @NotBlank
    private final String name;
    @NotBlank
    @Pattern(regexp = "^(.+)@(.+)$")
    private final String email;
    @NotBlank
    private final LocalDateTime createdAt;
    @NotBlank
    private final LocalDateTime updatedAt;

    @Builder
    public UserResponseDTO(Long id, String userId, String name, String email, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
