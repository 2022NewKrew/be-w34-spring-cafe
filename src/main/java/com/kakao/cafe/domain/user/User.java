package com.kakao.cafe.domain.user;

import com.kakao.cafe.domain.user.exception.UserLoginFailedException;

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
        checkMatchesPasswordForUpdate(confirmPassword);
        this.profile = profile;
        this.updatedAt = LocalDateTime.now();
    }

    public void updatePassword(String newPassword, String confirmPassword) {
        checkMatchesPasswordForUpdate(confirmPassword);
        this.password = newPassword;
        this.updatedAt = LocalDateTime.now();
    }

    public void checkMatchesPasswordForUpdate(String confirmPassword) {
        if(!password.equals(confirmPassword)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
    }

    public void checkMatchesPasswordForLogin(String confirmPassword) {
        if(!password.equals(confirmPassword)) {
            throw new UserLoginFailedException("비밀번호가 일치하지 않습니다.");
        }
    }

    public void checkEqualsUser(String currentUserId) {
        if(!userId.equals(currentUserId)) {
            throw new IllegalArgumentException("사용자의 정보를 수정할 수 없습니다.");
        }
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
