package com.kakao.cafe.domain;

import lombok.Getter;

import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.SimpleDateFormat;

import java.util.Date;

@Getter
@Setter
public class User {
    private long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date joinedAt;
    private String userId;
    private String password;
    private String name;
    private String email;




}
