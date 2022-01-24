package com.kakao.cafe.dto.user;

import com.kakao.cafe.domain.user.User;
import lombok.Data;

@Data
public class ShowUserDto {
    private String userId;
    private String email;
    private String name;

    public ShowUserDto(User user) {
        userId = user.getUserId();
        email = user.getEmail();
        name = user.getName();
    }

}
