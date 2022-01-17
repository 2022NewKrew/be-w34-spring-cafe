package com.kakao.cafe.web.user.dto;

import com.kakao.cafe.domain.user.Email;
import com.kakao.cafe.domain.user.Name;
import com.kakao.cafe.domain.user.User;

public class UserProfileResponse {
    private final Name name;
    private final Email email;

    public UserProfileResponse(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
    }

    public Name getName() {
        return name;
    }

    public Email getEmail() {
        return email;
    }
}
