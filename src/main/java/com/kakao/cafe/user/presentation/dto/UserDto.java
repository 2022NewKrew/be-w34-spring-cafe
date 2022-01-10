package com.kakao.cafe.user.presentation.dto;

import com.kakao.cafe.user.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserDto {
    private final String userId;
    private final String name;
    private final String email;

    public static UserDto of(User user){
        return new UserDto(user.getUserId(), user.getName(), user.getEmail());
    }
}
