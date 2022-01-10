package com.kakao.cafe.domain;


import com.kakao.cafe.controller.dto.UserJoinDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter(AccessLevel.PRIVATE)
public class User {
    private String userId;
    private String name;
    private String email;
    private String password;

    private User() {}
    public static User from(UserJoinDto dto) {
        User user = new User();
        user.setUserId(dto.getUserId());
        user.setEmail(dto.getEmail());
        user.setName(dto.getName());
        user.setPassword(dto.getPassword());
        return user;
    }


    public boolean chcekPassword(String password) {
        return this.password.equals(password);
    }

    public void updateEmailAndName(String email, String name) {
        this.email = email;
        this.name = name;
    }
}
