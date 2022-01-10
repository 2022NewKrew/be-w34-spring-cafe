package com.kakao.cafe.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

    private Long id;
    private String userID;
    private String password;
    private String name;
    private String email;

}
