package com.kakao.cafe.user.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserInfoDTO {
    private final int idx;
    private final String userId;
    private final String name;
    private final String email;
}
