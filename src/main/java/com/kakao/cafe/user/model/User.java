package com.kakao.cafe.user.model;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Setter
    private Long id;
    private String userId;
    private String name;
    private String password;
    private String email;
}
