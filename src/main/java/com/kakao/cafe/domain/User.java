package com.kakao.cafe.domain;

import com.kakao.cafe.dto.user.UserCreationDTO;
import com.kakao.cafe.dto.user.UserDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class User {
    private long id;
    private String email;
    private String nickname;
    private String password;
    private LocalDate createdAt;

    public User(UserCreationDTO dto) {
        this.email = dto.getEmail();
        this.nickname = dto.getNickname();
        this.password = dto.getPassword();
        createdAt = LocalDate.now();
    }

    public User(UserDTO dto) {
        this.id = dto.getId();
        this.email = dto.getEmail();
        this.nickname = dto.getNickname();
        this.password = dto.getPassword();
        this.createdAt = dto.getCreatedAt();
    }
}
