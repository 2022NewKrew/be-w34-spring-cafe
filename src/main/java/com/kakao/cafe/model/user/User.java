package com.kakao.cafe.model.user;

public class User {
    private final UserId userId;
    private final Password password;
    private Name name;
    private Email email;

    public User(UserId userId, Password password, Name name, Email email) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public boolean isUserId(UserId userId) {
        return this.userId.equals(userId);
    }

    public boolean isPassword(Password password) {
        return this.password.equals(password);
    }

    public UserId getUserId() {
        return userId;
    }

    public Password getPassword() {
        return password;
    }

    public Name getName() {
        return name;
    }

    public Email getEmail() {
        return email;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public void setEmail(Email email) {
        this.email = email;
    }
}
