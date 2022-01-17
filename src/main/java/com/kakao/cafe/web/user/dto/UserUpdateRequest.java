package com.kakao.cafe.web.user.dto;

import com.kakao.cafe.domain.user.Email;
import com.kakao.cafe.domain.user.Name;
import com.kakao.cafe.domain.user.Password;

public class UserUpdateRequest {
    private final Password password;
    private final Name name;
    private final Email email;

    public UserUpdateRequest(Password password, Name name, Email email) {
        this.password = password;
        this.name = name;
        this.email = email;
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
}
