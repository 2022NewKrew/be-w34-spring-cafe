package com.kakao.cafe.user.domain;

import com.kakao.cafe.user.dto.SignUpDTO;
import com.kakao.cafe.user.dto.UpdateDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class User {

    private Long id;

    private String userId;

    private String password;

    private String name;

    private String email;

    public User(Long id, SignUpDTO signUpDTO) {
        this.id = id;
        this.userId = signUpDTO.getUserId();
        this.password = signUpDTO.getPassword();
        this.name = signUpDTO.getName();
        this.email = signUpDTO.getEmail();
    }

    public void updateInfo(UpdateDTO updateDTO) {
        this.password = updateDTO.getPassword();
        this.name = updateDTO.getName();
        this.email = updateDTO.getEmail();
    }
}
