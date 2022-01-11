package com.kakao.cafe.dto.user;

import com.kakao.cafe.domain.Entity.User;
import lombok.*;

@Getter
@NoArgsConstructor
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

    @Builder
    public UserDto(String userId, String password, String name, String email) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
    }
}
