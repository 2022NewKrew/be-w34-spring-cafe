package com.kakao.cafe.response;

import com.kakao.cafe.domain.User;

public class UserListResponse {
    private final int id;
    private final String userId;
    private final String name;
    private final String email;

    private UserListResponse(int id, String userId, String name, String email) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.email = email;
    }

    public static UserListResponse of(User user) {
        return new UserListResponse(user.getId(), user.getUserId(), user.getUserName(), user.getEmail());
    }
}
