package com.kakao.cafe.user.dto.response;

import com.kakao.cafe.user.domain.User;
import com.kakao.cafe.user.domain.Users;
import java.util.ArrayList;
import java.util.List;

public class UsersResponse {

    private final List<UserResponse> users;

    public UsersResponse(List<UserResponse> users) {
        this.users = users;
    }

    public static UsersResponse toResponse(Users users) {
        List<UserResponse> userResponses = new ArrayList<>();
        for (User user : users.getUsers()) {
            userResponses.add(UserResponse.toResponse(user));
        }
        return new UsersResponse(userResponses);
    }

    public List<UserResponse> getUsers() {
        return users;
    }
}
