package com.kakao.cafe.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Calendar;

@Getter
@Setter
@Builder
public class User {
    private Long id;
    private final String email;
    private String password;
    private String nickName;
    private Calendar joinDate;
}
