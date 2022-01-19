package com.kakao.cafe.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class UserDto {

    private final Long id;
    private final String userId;
    private final String name;
    private final String email;
}
