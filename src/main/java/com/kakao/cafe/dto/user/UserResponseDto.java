package com.kakao.cafe.dto.user;

import com.kakao.cafe.domain.user.User;
import lombok.Getter;
import lombok.Setter;

import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class UserResponseDto {
    private String userId;
    private String email;
    private String name;
    private String regDate;

    public UserResponseDto(User user) {
        userId = user.getUserId();
        email = user.getEmail();
        name = user.getName();
        regDate = user.getRegDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd / HH:mm:ss"));
    }
}
