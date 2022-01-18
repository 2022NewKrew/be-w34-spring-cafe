package com.kakao.cafe.web.user.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    String userId;
    String password;
    String name;
    String email;
}
