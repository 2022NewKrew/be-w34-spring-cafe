package com.kakao.cafe.domain.user;

import lombok.*;

@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class User {
    private Long id;
    private String userId;
    private String email;
    private String nickname;
    private String password;
}
