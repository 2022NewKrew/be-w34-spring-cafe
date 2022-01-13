package com.kakao.cafe.domain.user;

import com.kakao.cafe.domain.user.exception.PasswordNotMatchException;
import org.springframework.core.style.ToStringCreator;

import java.time.LocalDate;
import java.util.Objects;

public class User {
    private Long id;
    private String userId;
    private String password;
    private String nickname;
    private String email;
    private LocalDate createdAt;

    public User(Long id, String userId, String password, String nickname, String email, LocalDate createdAt) {
        this.id = id;
        this.userId = userId;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.createdAt = createdAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void validatePassword(String password) {
        if (!getPassword().equals(password)) throw new PasswordNotMatchException();
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public String toString() {
        return new ToStringCreator(this)
                .append("id", getId())
                .append("userId", getUserId())
                .append("password", getPassword())
                .append("nickname", getNickname())
                .append("email", getEmail())
                .append("createdAt", getCreatedAt())
                .toString();
    }
}
