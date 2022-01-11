package com.kakao.cafe.user;

public class UserListResponse {
    public int index;
    public String userId;
    public String name;
    public String email;

    UserListResponse(int index, User user) {
        this.index = index;
        userId = user.getUserId();
        name = user.getName();
        email = user.getEmail();
    }
}
