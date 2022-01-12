package com.kakao.cafe.user.dto;

import com.kakao.cafe.user.domain.User;

public class UserListResponse {

    public int index;
    public String userId;
    public String name;
    public String email;

    public UserListResponse(int index, User user) {
        this.index = index;
        userId = user.getUserId();
        name = user.getName();
        email = user.getEmail();
    }
}
