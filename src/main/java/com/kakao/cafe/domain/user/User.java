package com.kakao.cafe.domain.user;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
public class User {

    @Setter
    private UUID id;

    private final UserName userName;
    private final Password password;
    private Name name;
    private Email email;

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

    public void update(User user) {
        if (this.id.equals(user.getId())) {
            name = user.getName();
            email = user.getEmail();
        }
    }
}
