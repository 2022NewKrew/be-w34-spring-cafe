package com.kakao.cafe.model;

import com.kakao.cafe.utility.NullChecker;

public class User {
    private static final int ALLOWED_LENGTH_USERID_AND_PASSWORD = 16;
    private static final int ALLOWED_LENGTH_NAME = 8;
    private static final int ALLOWED_LENGTH_EMAIL = 24;

    private final String userId;
    private final String password;
    private String name;
    private String email;

    public User(String userId, String password, String name, String email) {
        checkUserId(userId);
        checkPassword(password);
        checkName(name);
        checkEmail(email);

        this.userId = userId;
        this.password = password.trim();
        this.name = name;
        this.email = email;
    }

    public boolean isUserId(String userId) {
        return this.userId.equals(userId);
    }

    public boolean isPassword(String password) {
        return this.password.equals(password);
    }

    private void checkUserId(String userId) {
        NullChecker.checkNotNull(userId);

        if (userId.length() > ALLOWED_LENGTH_USERID_AND_PASSWORD) {
            throw new IllegalArgumentException("유저아이디의 길이가 너무 깁니다.");
        }
    }

    private void checkPassword(String password) {
        NullChecker.checkNotNull(password);

        if (password.length() > ALLOWED_LENGTH_USERID_AND_PASSWORD) {
            throw new IllegalArgumentException("패스워드의 길이가 너무 깁니다.");
        }
    }

    private void checkName(String name) {
        NullChecker.checkNotNull(name);

        if (name.length() > ALLOWED_LENGTH_NAME) {
            throw new IllegalArgumentException("이름의 길이가 너무 깁니다.");
        }
    }

    private void checkEmail(String email) {
        NullChecker.checkNotNull(email);

        if (email.length() > ALLOWED_LENGTH_EMAIL) {
            throw new IllegalArgumentException("이메일의 길이가 너무 깁니다.");
        }
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

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
