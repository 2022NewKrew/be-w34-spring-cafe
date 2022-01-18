package com.kakao.cafe.dto;

import com.kakao.cafe.model.User;
import lombok.Getter;


@Getter
public class UserShowDto {
    private Integer id;
    private String userId;
    private String password;
    private String userName;
    private String email;

    public UserShowDto(User user) {
        this.id = user.getId();
        this.userId = user.getUserId();
        this.password = user.getPassword();
        this.userName = user.getUserName();
        this.email = user.getEmail();
    }
}
