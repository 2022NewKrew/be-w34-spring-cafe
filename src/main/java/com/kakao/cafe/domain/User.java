package com.kakao.cafe.domain;

import com.kakao.cafe.dto.UserDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class User {
    private long key;
    private String id;
    private String name;
    private String pw;
    private String email;

    public static User fromDTO(UserDTO userDTO) {
        User user = User.builder()
                .key(userDTO.getKey())
                .id(userDTO.getId())
                .name(userDTO.getName())
                .pw(userDTO.getPw())
                .email(userDTO.getEmail())
                .build();
        return user;
    }

    public UserDTO getDTO() {
        UserDTO userDTO = UserDTO.builder()
                .key(key)
                .id(id)
                .name(name)
                .pw(pw)
                .email(email)
                .build();
        return userDTO;
    }
}