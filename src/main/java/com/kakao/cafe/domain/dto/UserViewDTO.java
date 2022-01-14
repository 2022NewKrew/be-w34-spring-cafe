package com.kakao.cafe.domain.dto;


import com.kakao.cafe.domain.model.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class UserViewDTO {
    private final String userId;
    private final String name;
    private final String email;

    public UserViewDTO(User user){
        this.userId = user.getUserId();
        this.name = user.getName();
        this.email = user.getEmail();
    }
}

