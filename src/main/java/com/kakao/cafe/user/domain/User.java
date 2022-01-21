package com.kakao.cafe.user.domain;

import lombok.Builder;
import lombok.With;

import java.time.LocalDateTime;

@Builder
public class User {
    @With
    private final Long id;
    private final String username;
    private final String password;
    private final String email;
    private final String displayName;
    private final String status;
    private final LocalDateTime createdAt;
    private final LocalDateTime lastModifiedAt;

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", displayName='" + displayName + '\'' +
                ", status='" + status + '\'' +
                ", createdAt=" + createdAt +
                ", lastModifiedAt=" + lastModifiedAt +
                '}';
    }

    public boolean isPasswordMatched(String password) {
        return this.password.equals(password);
    }
}
