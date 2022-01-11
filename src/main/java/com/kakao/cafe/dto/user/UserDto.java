package com.kakao.cafe.dto.user;

import com.kakao.cafe.domain.Entity.User;
import lombok.*;

@Getter
// @Builder
public class UserDto {
    private String userId;
    private String password;
    private String name;
    private String email;

    public User toEntity() {
        User user = User.builder()
                .userId(this.userId)
                .password(this.password)
                .name(this.name)
                .email(this.email)
                .build();
        return user;
    }

    public UserDto(String userId, String name, String email) {
        this.userId = userId;
        this.name = name;
        this.email = email;
    }
}
