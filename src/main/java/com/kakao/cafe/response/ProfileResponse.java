package com.kakao.cafe.response;

import com.kakao.cafe.domain.User;

public class ProfileResponse {

    private final String userId;
    private final String name;
    private final String email;

    private ProfileResponse(String userId, String name, String email) {
        this.userId = userId;
        this.name = name;
        this.email = email;
    }

    public static ProfileResponse of(User user) {
        return new ProfileResponse(user.getUserId(), user.getUserName(), user.getEmail());
    }
}
