package com.kakao.cafe.domain.user;

import com.kakao.cafe.exception.InvalidPasswordException;

public class User {

    private Long id;
    private String username;
    private String nickname;
    private String email;
    private String password;

    public User(String username, String nickname, String email, String password) {
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

    private void validatePassword(String newPassword) {
        if (!password.equals(newPassword)) {
            throw new InvalidPasswordException("비밀번호가 일치하지 않습니다.");
        }
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isEqualUserId(long id) {
        return this.id == id;
    }

    public Long getId() {
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

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof User) {
            return isEqualUserId(((User) obj).id);
        }
        return false;
    }
}
