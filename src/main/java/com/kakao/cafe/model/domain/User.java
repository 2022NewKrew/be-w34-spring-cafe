package com.kakao.cafe.model.domain;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String userId;
    private String password;
    private String name;
    private String email;
    private long id;
}