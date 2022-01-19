package com.kakao.cafe.domain.user;

import com.kakao.cafe.exception.InvalidPasswordException;

public class User {

    private final Long id;
    private final String username;
    private String nickname;
    private String email;
    private String password;

    public User(String username, String nickname, String email, String password) {
        this(null, username, nickname, email, password);
    }

    public User(Long id, String username, String nickname, String email, String password) {
        this.id = id;
        this.username = username;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
    }

    public void update(String nickname, String email, String password, String newPassword) {
        validatePassword(password);
        this.nickname = nickname;
        this.email = email;
        this.password = newPassword;
    }

    private void validatePassword(String inputPassword) {
        if (isNotEqualPassword(inputPassword)) {
            throw new InvalidPasswordException("비밀번호가 일치하지 않습니다.");
        }
    }

    public boolean isNotEqualPassword(String inputPassword) {
        return !password.equals(inputPassword);
    }

    public Long getId() {
        if (id == null) {
            throw new AssertionError("id 값이 설정되지 않은 엔티티입니다.");
        }
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getNickname() {
        return nickname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof User) {
            return this.id.equals(((User) obj).id);
        }
        return false;
    }
}
