package com.kakao.cafe.user.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserProfileDTO {
    private final String name;
    private final String email;
    private final String pictureAddress;
}
