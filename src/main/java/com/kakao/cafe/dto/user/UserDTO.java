package com.kakao.cafe.dto.user;

import com.kakao.cafe.domain.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserDTO {
    private long id;
    private String email;
    private String nickname;
    private String password;
    private LocalDate createdAt;
}
