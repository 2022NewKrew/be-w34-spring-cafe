package com.kakao.cafe.domain.user;

import java.util.UUID;

public class User {

    private final UUID id;
    private final UserName userName;
    private final Password password;
    private final Name name;
    private final Email email;

    public User(UUID id, UserName userName, Password password, Name name, Email email) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public User(UserName userName, Password password, Name name, Email email) {
        this(null, userName, password, name, email);
    }

    public UUID getId() {
        return id;
    }

    public UserName getUserName() {
        return userName;
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

    public boolean isUserSame(User user) {
        return id.equals(user.getId());
    }
}
