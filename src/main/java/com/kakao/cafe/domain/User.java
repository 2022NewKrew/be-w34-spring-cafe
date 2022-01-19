package com.kakao.cafe.domain;

import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.Objects;

public class User {
    @NotBlank
    private final Long id;
    @NotBlank
    private final String userId;
    @NotBlank
    private String password;
    @NotBlank
    private String name;
    @NotBlank
    @Pattern(regexp = "^(.+)@(.+)$")
    private String email;
    @NotBlank
    private final LocalDateTime createdAt;
    @Nullable
    private LocalDateTime updatedAt;

    public static User of(Long id, String userId, String password, String name, String email, LocalDateTime createdAt) {
        return new User(id, userId, password, name, email, createdAt);
    }

    private User(Long id, String userId, String password, String name, String email, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
        this.createdAt = createdAt;
    }

    public boolean validatePassword(String password) {
        return password.equals(this.password);
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

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
