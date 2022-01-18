package com.kakao.cafe.user.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Objects;

@ToString(exclude = {"password"})
@EqualsAndHashCode
@Getter
public class UserAccount {

    private final Long userAccountId;
    private String username;
    private final String password;
    private String email;
    private final LocalDateTime createdAt;

    @Builder
    public UserAccount(Long userAccountId, String username, String password, String email, LocalDateTime createdAt) {
        this.userAccountId = userAccountId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.createdAt = createdAt;
    }

    public void updateUsername(String username) {
        this.username = username;
    }

    public void updateEmail(String email) {
        this.email = email;
    }

    public boolean checkEmail(String email) {
        return this.email.equals(email);
    }

    public boolean checkPassword(String password) {
        return Objects.equals(this.password, password);
    }
}
