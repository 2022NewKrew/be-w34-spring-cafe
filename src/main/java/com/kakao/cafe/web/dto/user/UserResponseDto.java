package com.kakao.cafe.web.dto.user;

import com.kakao.cafe.domain.user.User;
import lombok.Getter;

@Getter
public class UserResponseDto {
    private final int id;
    private final String accId; // accountId
    private final String name;
    private final String email;

    public UserResponseDto(User user){
        this.id = user.getId();
        this.accId = user.getAccId();
        this.name = user.getName();
        this.email = user.getEmail();
    }
}
