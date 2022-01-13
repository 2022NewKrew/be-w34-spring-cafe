package com.kakao.cafe.dto;

import com.kakao.cafe.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserProfileDto {
    private String userId;
    private String email;
    private String name;
}
