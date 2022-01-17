package com.kakao.cafe.web.user.dto;

import com.kakao.cafe.domain.user.Email;
import com.kakao.cafe.domain.user.Name;
import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.UserId;

public class UserListResponse {
    private final UserId userId;
    private final Name name;
    private final Email email;

    public UserListResponse(User user) {
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
        if(!(obj instanceof UserListResponse)) {
            return false;
        }

        UserListResponse userListResponse = (UserListResponse) obj;
        return userId.equals(userListResponse.userId) &&
                name.equals(userListResponse.name) &&
                email.equals(userListResponse.email);
    }
}
