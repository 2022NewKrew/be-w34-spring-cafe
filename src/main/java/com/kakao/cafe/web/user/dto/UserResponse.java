package com.kakao.cafe.web.user.dto;

import com.kakao.cafe.domain.user.Email;
import com.kakao.cafe.domain.user.Name;
import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.UserId;

public class UserResponse {
    private final UserId userId;
    private final Name name;
    private final Email email;

    public UserResponse(User user) {
        this.userId = user.getUserId();
        this.name = user.getName();
        this.email = user.getEmail();
    }

    public UserId getUserId() {
        return userId;
    }

    public Name getName() {
        return name;
    }

    public Email getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof UserResponse)) {
            return false;
        }

        UserResponse userResponse = (UserResponse) obj;
        return userId.equals(userResponse.userId) &&
                name.equals(userResponse.name) &&
                email.equals(userResponse.email);
    }
}
