package com.kakao.cafe.domain.dto;


import com.kakao.cafe.domain.model.User;
import lombok.Getter;

import java.util.Objects;

@Getter
public class UserViewDto {
    private final String userId;
    private final String name;
    private final String email;

    public UserViewDto(User user){
        validateUser(user);

        this.userId = user.getUserId();
        this.name = user.getName();
        this.email = user.getEmail();
    }

    private void validateUser(User user){
        if(Objects.isNull(user)) throw new IllegalArgumentException();
    }
}

