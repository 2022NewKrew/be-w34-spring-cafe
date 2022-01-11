package com.kakao.cafe.model.domain;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String id;
    private String password;
    private String name;
    private String email;
}