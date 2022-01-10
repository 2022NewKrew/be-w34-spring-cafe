package com.kakao.cafe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class SignUpDTO {

    private String userId;

    private String password;

    private String name;

    private String email;

}
