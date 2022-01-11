package com.kakao.cafe.user.domain;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.regex.Pattern;

public class User {
    private static final String EMAIL_PATTERN = "^(.+)@(\\S+)$";
    private static final String INVALID_INPUT_EMAIL = "올바르지 않은 이메일입니다.";
    
    private final String email;
    private final String nickname;
    private final String password;
    private final LocalDateTime createdDate;

    public User(String email, String nickname, String password) {
        validate(email);
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.createdDate = LocalDateTime.now();
    }

    private void validate(String email) {
        if(!Pattern.matches(EMAIL_PATTERN,email)){
            throw new IllegalArgumentException(INVALID_INPUT_EMAIL);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(email, user.email);
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
}
