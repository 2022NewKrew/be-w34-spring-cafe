package com.kakao.cafe.domain;

import com.kakao.cafe.dto.user.UserCreationDto;
import com.kakao.cafe.dto.user.UserDto;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private long id;
    private String email;
    private String nickname;
    private String password;
    private LocalDate createdAt;

    public User(UserCreationDto dto) {
        this.email = dto.getEmail();
        this.nickname = dto.getNickname();
        this.password = dto.getPassword();
        createdAt = LocalDate.now();
    }

    public User(UserDto dto) {
        this.id = dto.getId();
        this.email = dto.getEmail();
        this.nickname = dto.getNickname();
        this.password = dto.getPassword();
        this.createdAt = dto.getCreatedAt();
    }
}
