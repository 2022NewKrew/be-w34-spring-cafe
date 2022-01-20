package com.kakao.cafe.web;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class User {
    private String id;
    private String password;
    private String name;
    private String email;
}
