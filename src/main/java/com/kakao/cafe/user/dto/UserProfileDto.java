package com.kakao.cafe.user.dto;

import com.kakao.cafe.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UserProfileDto {
    private final String name;
    private final String email;

    public static UserProfileDto of(User user){
        return UserProfileDto.builder()
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }
}
