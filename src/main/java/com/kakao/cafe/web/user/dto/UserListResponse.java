package com.kakao.cafe.web.user.dto;

import java.util.Collections;
import java.util.List;

public class UserListResponse {
    private final List<UserResponse> users;

    public UserListResponse(List<UserResponse> userResponses) {
        this.users = Collections.unmodifiableList(userResponses);
    }

    public List<UserResponse> getUsers() {
        return users;
    }

    @Override
    public int hashCode() {
        return users.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        }
        if(!(obj instanceof UserListResponse)) {
            return false;
        }

        UserListResponse other = (UserListResponse) obj;
        return users.equals(other.getUsers());
    }
}
