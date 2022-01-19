package com.kakao.cafe.dto.user;

import com.kakao.cafe.domain.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignUpDto {
    private String userId;
    private String password;
    private String name;
    private String email;

    public User toEntity() {
        User user = new User(userId, password, name, email);
        return user;
    }
}
