package com.kakao.cafe.domain.user;

import lombok.Builder;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

@ToString(exclude = {"password"})
@Builder
public class UserAccount {

    private static final AtomicLong idGenerator;

    private final Long userAccountId;

    private final String username;

    private final String password;

    private final String email;

    private final LocalDate createdAt;

    static {
        idGenerator = new AtomicLong();
    }

    public UserAccount(Long userAccountId, String username, String password, String email, LocalDate createdAt) {
        this.userAccountId = Objects.requireNonNullElseGet(userAccountId, idGenerator::getAndIncrement);
        this.username = username;
        this.password = password;
        this.email = email;
        this.createdAt = createdAt;
    }

    public boolean checkEmail(String email) {
        return this.email.equals(email);
    }

    public boolean checkUserAccountId(Long id) {
        return Objects.equals(this.userAccountId, id);
    }

    public Long getUserAccountId() {
        return userAccountId;
    }

    public String getUsername() {
        return username;
    }
}
