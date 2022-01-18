package com.kakao.cafe.web.user.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserCreateDTO {
    String userId;
    String password;
    String name;
    String email;
}
