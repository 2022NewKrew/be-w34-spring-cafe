package com.kakao.cafe.dto;

import com.kakao.cafe.domain.User;

public class UserProfileResponse {
    String nickname;
    String email;

    public UserProfileResponse(User user){
        nickname = user.getNickname();
        email = user.getEmail();
    }
}
