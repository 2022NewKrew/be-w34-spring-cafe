package com.kakao.cafe.user.dto;

import com.kakao.cafe.user.domain.User;
import lombok.Setter;

public class UserSaveRequest {

    @Setter public String userId;
    @Setter public String password;
    @Setter public String name;
    @Setter public String email;

    public User toUser() {
        return new User(
                this.userId,
                this.password,
                this.name,
                this.email
        );
    }
}
