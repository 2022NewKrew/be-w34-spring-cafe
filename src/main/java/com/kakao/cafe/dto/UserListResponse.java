package com.kakao.cafe.dto;

import com.kakao.cafe.domain.User;

public class UserListResponse {
    String id;
    String nickname;
    String name;
    String email;

    public UserListResponse(User user){
        id = String.valueOf(user.getId());
        nickname = user.getNickname();
        name = user.getName();
        email = user.getEmail();
    }
}
