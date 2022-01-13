package com.kakao.cafe.model.domain;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private long id;
    private String userId;
    private String password;
    private String name;
    private String email;
}