package com.kakao.cafe.user.domain;

import java.time.LocalDateTime;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class User {

    @NotNull
    private final Long id;

    @NotBlank
    @Email
    private final String email;

    @NotBlank
    private final String nickname;

    @NotBlank
    private final String password;

    @NotNull
    private final LocalDateTime createdDate;

    public User(Long id, String email, String nickname, String password, LocalDateTime createdDate) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.createdDate = createdDate;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getNickname() {
        return nickname;
    }

    public String getPassword() {
        return password;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public boolean validateAuth(Long userId) {
        return this.id.equals(userId);
    }
}
