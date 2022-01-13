package com.kakao.cafe.dto.user;

import com.kakao.cafe.domain.user.User;
import lombok.Getter;

@Getter
public class UserResDto {
    private Long id;
    private String userId;
    private String password;
    private String name;
    private String email;

    public UserResDto(User user){
        this.id = user.getId();
        this.userId = user.getUserId();
        this.password = user.getPassword();
        this.name = user.getName();
        this.email = user.getEmail();
    }
}
