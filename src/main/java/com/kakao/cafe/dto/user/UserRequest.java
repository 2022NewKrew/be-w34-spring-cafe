package com.kakao.cafe.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class UserRequest {
    private String userId;
    private String password;
    private String name;
    private String email;
}
