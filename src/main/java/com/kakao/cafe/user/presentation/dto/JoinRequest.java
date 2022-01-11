package com.kakao.cafe.user.presentation.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class JoinRequest {
    private String userId;
    private String password;
    private String name;
    private String email;
}
