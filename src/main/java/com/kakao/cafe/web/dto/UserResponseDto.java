package com.kakao.cafe.web.dto;

import com.kakao.cafe.domain.user.User;

import java.util.ArrayList;
import java.util.List;

public class UserResponseDto {

    private Long id;
    private String userId;
    private String name;
    private String email;

    private UserResponseDto() {}

    public static List<UserResponseDto> from(List<User> userList) {
        List<UserResponseDto> result = new ArrayList<>();
        for (User user : userList) {
            UserResponseDto responseDto = new UserResponseDto();
            responseDto.setId(user.getId());
            responseDto.setUserId(user.getUserId());
            responseDto.setName(user.getName());
            responseDto.setEmail(user.getEmail());
            result.add(responseDto);
        }
        return result;
    }

    public Long getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
