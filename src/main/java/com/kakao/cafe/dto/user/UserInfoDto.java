package com.kakao.cafe.dto.user;

import com.kakao.cafe.domain.Entity.User;
import lombok.Getter;

@Getter
public class UserInfoDto {
    private String userId;
    private String name;
    private String email;

    public UserInfoDto(User user) {
        this.userId = user.getUserId();
        this.name = user.getName();
        this.email = user.getEmail();
    }
}
