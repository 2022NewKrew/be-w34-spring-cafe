package com.kakao.cafe.web.user.dto;

import com.kakao.cafe.domain.user.*;

public class UserCreateRequest {
    private final UserId userId;
    private final Password password;
    private final Name name;
    private final Email email;

    public UserCreateRequest(String userId, String password, String name, String email) {
        this(new UserId(userId), new Password(password), new Name(name), new Email(email));
    }

    public UserCreateRequest(UserId userId, Password password, Name name, Email email) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public User toEntity() {
        User user = new User();

        user.setUserId(userId);
        user.setPassword(password);
        user.setName(name);
        user.setEmail(email);

        return user;
    }
}
