package com.kakao.cafe.domain;

import com.kakao.cafe.dto.UserForm;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class User {
    private UserId userId;
    private Password password;
    private Name name;
    private Email email;

    public User(UserForm userForm) {
        this(new UserId(userForm.getUserId()),
                new Password(userForm.getPassword()),
                new Name(userForm.getName()),
                new Email(userForm.getEmail()));
    }

    public String getUserId() {
        return userId.getUserId();
    }

    public String getPassword() {
        return password.getPassword();
    }

    public String getName() {
        return name.getName();
    }

    public String getEmail() {
        return email.getEmail();
    }
}
