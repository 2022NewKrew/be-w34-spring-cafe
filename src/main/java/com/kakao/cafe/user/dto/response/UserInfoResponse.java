package com.kakao.cafe.user.dto.response;

import com.kakao.cafe.user.entity.User;

public class UserInfoResponse {

    private final Integer id;
    private final String userId;
    private final String name;
    private final String email;

    public UserInfoResponse(User user) {
        this(user.getId(),
             user.getUserId(),
             user.getName(),
             user.getEmail());
    }

    public UserInfoResponse(Integer id, String userId, String name, String email) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.email = email;
    }

    public Integer getId() { return this.id; }
    public String getUserId() { return this.userId; }
    public String getName() { return this.name; }
    public String getEmail() { return this.email; }
}
