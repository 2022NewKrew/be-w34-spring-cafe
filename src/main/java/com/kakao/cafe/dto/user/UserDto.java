package com.kakao.cafe.dto.user;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserDto {
    private long id;
    private String email;
    private String nickname;
    private String password;
    private LocalDate createdAt;
}
