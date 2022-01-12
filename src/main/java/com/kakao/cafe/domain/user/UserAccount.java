package com.kakao.cafe.domain.user;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString(exclude = {"password"})
@EqualsAndHashCode
@Getter
public class UserAccount {

    private final Long userAccountId;
    private final String username;
    private final String password;
    private final String email;
    private final LocalDateTime createdAt;

    @Builder
    public UserAccount(Long userAccountId, String username, String password, String email, LocalDateTime createdAt) {
        this.userAccountId = userAccountId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.createdAt = createdAt;
    }

    public boolean checkEmail(String email) {
        return this.email.equals(email);
    }

    public Long getUserAccountId() {
        return userAccountId;
    }

    public String getUsername() {
        return username;
    }
}
