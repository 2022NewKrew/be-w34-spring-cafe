package com.kakao.cafe.domain.user;

import com.kakao.cafe.dto.UserSaveDto;
import lombok.Getter;
import lombok.Setter;

@Getter
public class User {
    private int id;
    private String userId;
    private String password;
    private String name;
    private String email;

    public User(int id, UserSaveDto userSaveDto){
        this.id = id;
        this.userId = userSaveDto.getUserId();
        this.password = userSaveDto.getPassword();
        this.name = userSaveDto.getName();
        this.email = userSaveDto.getEmail();
    }
}
