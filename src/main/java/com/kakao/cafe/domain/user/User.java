package com.kakao.cafe.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class User {
    private Long id;
    private String userId;
    private String email;
    private String nickname;
    private String password;
}
