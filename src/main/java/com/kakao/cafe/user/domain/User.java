package com.kakao.cafe.user.domain;

import com.kakao.cafe.user.repository.UserRepository;
import com.kakao.cafe.user.repository.UserRepositoryImpl;
import java.time.LocalDateTime;
import java.util.regex.Pattern;

public class User {
    private static final String EMAIL_PATTERN = "^(.+)@(\\S+)$";
    private static final String INVALID_INPUT_EMAIL_MESSAGE = "올바르지 않은 이메일입니다.";

    private final Long id;
    private final String email;
    private final String nickname;
    private final String password;
    private final LocalDateTime createdDate;

    public User(Long id, String email, String nickname, String password) {
        validate(email);
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.createdDate = LocalDateTime.now();
    }

    private void validate(String email) {
        if(!Pattern.matches(EMAIL_PATTERN,email)){
            throw new IllegalArgumentException(INVALID_INPUT_EMAIL_MESSAGE);
        }
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

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }
}
