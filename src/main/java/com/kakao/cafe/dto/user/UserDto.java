package com.kakao.cafe.dto.user;

import com.kakao.cafe.domain.user.User;

public class UserDto {

    private final Long userId;
    private final String username;
    private final String nickname;
    private final String email;

    public UserDto(User user) {
        this.userId = user.getId();
        this.username = user.getUsername();
        this.nickname = user.getNickname();
        this.email = user.getEmail();
    }
}
