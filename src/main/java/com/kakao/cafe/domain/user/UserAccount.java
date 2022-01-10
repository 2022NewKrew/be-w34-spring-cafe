package com.kakao.cafe.domain.user;

import org.springframework.cglib.core.Local;

import java.time.LocalDate;

public class UserAccount {

    private final Long userAccountId;

    private final String username;

    private final String password;

    private final String email;

    private final LocalDate createdAt;

    public UserAccount(Long userAccountId, String username, String password, String email, LocalDate createdAt) {
        this.userAccountId = userAccountId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.createdAt = createdAt;
    }

    public Long getUserAccountId() {
        return userAccountId;
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

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "userAccountId=" + userAccountId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Long builderUserAccountId;
        private String builderUsername;
        private String builderPassword;
        private String builderEmail;
        private LocalDate builderCreatedAt;

        public UserAccount build() {
            return new UserAccount(
                    builderUserAccountId,
                    builderUsername,
                    builderPassword,
                    builderEmail,
                    builderCreatedAt);
        }

        public Builder userAccountId(Long id) {
            this.builderUserAccountId = id;
            return this;
        }

        public Builder username(String username) {
            this.builderUsername = username;
            return this;
        }

        public Builder password(String password) {
            this.builderPassword = password;
            return this;
        }

        public Builder email(String email) {
            this.builderEmail = email;
            return this;
        }

        public Builder createdAt(LocalDate createdAt) {
            this.builderCreatedAt = createdAt;
            return this;
        }
    }
}
