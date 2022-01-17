package com.kakao.cafe.dto.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserJoinDto {
    private final String email;
    private String password;
    private final String nickName;
}
