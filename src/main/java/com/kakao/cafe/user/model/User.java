package com.kakao.cafe.user.model;

import com.kakao.cafe.user.constraint.Email;
import com.kakao.cafe.user.constraint.Name;
import com.kakao.cafe.user.constraint.Password;
import com.kakao.cafe.user.constraint.UserId;

public class User {
    @UserId
    private final String userId;

    @Password
    private transient String plainPassword;

    private String hashedPassword;

    @Name
    private String name;

    @Email
    private String email;

    public User(String userId, String plainPassword, String name, String email) {
        this.userId = userId;
        this.plainPassword = plainPassword;
        this.name = name;
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPlainPassword() {
        return plainPassword;
    }

    public void setPlainPassword(String plainPassword) {
        this.plainPassword = plainPassword;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }
}
