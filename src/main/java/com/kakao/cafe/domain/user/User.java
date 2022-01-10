package com.kakao.cafe.domain.user;

public class User {

    private final UserId id;
    private final Password password;
    private final Name name;
    private final Email email;

    public User(UserId id, Password password, Name name, Email email) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public UserId getId() {
        return id;
    }

    public Name getName() {
        return name;
    }

    public Email getEmail() {
        return email;
    }
}
