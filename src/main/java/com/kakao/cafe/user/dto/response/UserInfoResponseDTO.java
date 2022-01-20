package com.kakao.cafe.user.dto.response;

import lombok.Getter;

import com.kakao.cafe.user.entity.User;

@Getter
public class UserInfoResponseDTO {
    private int id;
    private String userId;
    private String password;
    private String name;
    private String email;

    public UserInfoResponseDTO(User user) {
        this.id = user.getId();
        this.userId = user.getUserId();
        this.password = user.getPassword();
        this.name = user.getName();
        this.email = user.getEmail();
    }
}
