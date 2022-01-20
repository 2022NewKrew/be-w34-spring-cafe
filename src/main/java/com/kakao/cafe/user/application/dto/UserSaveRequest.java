package com.kakao.cafe.user.application.dto;

import com.kakao.cafe.user.domain.User;
import lombok.Setter;

@Setter
public class UserSaveRequest {

    public String userId;
    public String password;
    public String name;
    public String email;

    public User toUser() {
        return User.valueOf(this.userId, this.password, this.name, this.email);
    }
}
