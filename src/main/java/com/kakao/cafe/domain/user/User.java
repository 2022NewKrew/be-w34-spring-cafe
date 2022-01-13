package com.kakao.cafe.domain.user;

import java.time.LocalDateTime;

public class User {

    private Long id;
    private String userId;
    private String password;
    private Profile profile;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public User(String userId, String password, Profile profile) {
        this(userId, password, profile, LocalDateTime.now(), LocalDateTime.now());
    }

    public User(String userId, String password, Profile profile, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.userId = userId;
        this.password = password;
        this.profile = profile;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void updateProfile(Profile profile, String confirmPassword) {
        if(!matchesPassword(confirmPassword)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        this.profile = profile;
        this.updatedAt = LocalDateTime.now();
    }

    public void updatePassword(String newPassword, String confirmPassword) {
        if(!matchesPassword(confirmPassword)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        this.password = newPassword;
        this.updatedAt = LocalDateTime.now();
    }

    public boolean matchesPassword(String confirmPassword) {
        return password.equals(confirmPassword);
    }

    public Long getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return profile.getName();
    }

    public String getEmail() {
        return profile.getEmail();
    }

    public String getPassword() {
        return password;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
