package com.kakao.cafe.user.domain;

import lombok.Builder;
import lombok.With;

import java.time.LocalDateTime;

@Builder
public class User {
    @With
    private final Long id;
    private final String email;
    private final String username;
    private final String password;
    private final String status;
    private final String displayName;
    private final LocalDateTime createdAt;
    private final LocalDateTime lastModifiedAt;

    public User(Long id, String email, String username, String password, String status, String displayName,
                LocalDateTime createdAt, LocalDateTime lastModifiedAt) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.status = status;
        this.displayName = displayName;
        this.createdAt = createdAt;
        this.lastModifiedAt = lastModifiedAt;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getStatus() {
        return status;
    }

    public String getDisplayName() {
        return displayName;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getLastModifiedAt() {
        return lastModifiedAt;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", email='" + email + '\'' + ", username='" + username + '\'' + ", status='" + status + '\'' + ", displayName='" + displayName + '\'' + ", createdAt=" + createdAt + ", lastModifiedAt=" + lastModifiedAt + '}';
    }
}
