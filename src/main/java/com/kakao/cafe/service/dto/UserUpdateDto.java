package com.kakao.cafe.service.dto;

import com.kakao.cafe.controller.dto.request.UserUpdateRequestDto;
import lombok.Getter;

@Getter
public class UserUpdateDto {

    private final String userId;
    private final String name;
    private final String password;
    private final String email;

    public UserUpdateDto(String userId, UserUpdateRequestDto userUpdateRequestDto) {
        this.userId = userId;
        this.name = userUpdateRequestDto.getName();
        this.password = userUpdateRequestDto.getPassword();
        this.email = userUpdateRequestDto.getEmail();
    }
}
