package com.kakao.cafe.domain.dto;


import com.kakao.cafe.domain.model.User;
import lombok.Getter;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Objects;

@Getter
public class UserViewDTO {
    private final String userId;
    private final String name;
    private final String email;

    public UserViewDTO(User user){
        validateUser(user);

        this.userId = user.getUserId();
        this.name = user.getName();
        this.email = user.getEmail();
    }

    private void validateUser(User user){
        if(Objects.isNull(user)) throw new IllegalArgumentException();
    }
}

