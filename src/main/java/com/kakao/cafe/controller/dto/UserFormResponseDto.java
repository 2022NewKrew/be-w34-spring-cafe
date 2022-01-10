package com.kakao.cafe.controller.dto;

import com.kakao.cafe.domain.User;
import lombok.Getter;
import lombok.Setter;

// 정보 수정할때 이름과 이메일만 내보내는 용도로.
@Setter
@Getter
public class UserFormResponseDto {
    private String userId;
    private String name;
    private String email;

    public static UserFormResponseDto from(User user) {
        UserFormResponseDto userFormResponseDto = new UserFormResponseDto();
        userFormResponseDto.setUserId(user.getUserId());
        userFormResponseDto.setEmail(user.getEmail());
        userFormResponseDto.setName(user.getName());
        return userFormResponseDto;
    }
}
