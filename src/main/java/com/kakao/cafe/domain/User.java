package com.kakao.cafe.domain;

import com.kakao.cafe.dto.UserDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
public class User {

    @Setter
    private Long id;

    private String userId;

    private String password;

    private String name;

    private String email;

    public User(UserDTO userDTO){
        this.userId = userDTO.getUserId();
        this.password = userDTO.getPassword();
        this.name = userDTO.getName();
        this.email = userDTO.getEmail();
    }
}
