package com.kakao.cafe.web.dto;

import com.kakao.cafe.domain.user.User;

public class UserListResponse {
    private final String userId;
    private final String name;
    private final String email;

    public UserListResponse(User user) {
        this.userId = user.getUserId();
        this.name = user.getName();
        this.email = user.getEmail();
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
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
