package com.kakao.cafe.model.domain;

import com.kakao.cafe.model.dto.UserDTO;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private long id;
    private String userId;
    private String password;
    private String name;
    private String email;

    public User(long id, UserDTO userDTO) {
        this.id = id;
        this.userId = userDTO.getUserId();
        this.password = userDTO.getPassword();
        this.name = userDTO.getName();
        this.email = userDTO.getEmail();
    }
}