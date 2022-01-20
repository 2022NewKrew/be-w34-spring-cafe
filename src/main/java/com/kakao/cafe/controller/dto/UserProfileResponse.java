package com.kakao.cafe.controller.dto;

import com.kakao.cafe.domain.User;

public class UserProfileResponse {
    private String id;
    private String nickname;
    private String name;
    private String email;

    public UserProfileResponse(User user){
        id = String.valueOf(user.getId());
        nickname = user.getNickname();
        name = user.getName();
        email = user.getEmail();
    }
}
