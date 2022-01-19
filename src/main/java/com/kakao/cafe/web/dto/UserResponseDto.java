package com.kakao.cafe.web.dto;

import com.kakao.cafe.domain.user.User;

import java.util.ArrayList;
import java.util.List;

public class UserResponseDto {

    private final String userId;
    private final String name;
    private final String email;

    private UserResponseDto(String userId, String name, String email) {
        this.userId = userId;
        this.name = name;
        this.email = email;
    }

    public static List<UserResponseDto> from(List<User> userList) {
        List<UserResponseDto> result = new ArrayList<>();
        for (User user : userList) {
            UserResponseDto responseDto = new UserResponseDto(user.getUserId(), user.getName(), user.getEmail());
            result.add(responseDto);
        }
        return result;
    }
}
