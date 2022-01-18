package com.kakao.cafe.domain.entity;

import lombok.Getter;

import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.SimpleDateFormat;

import java.util.Date;

@Getter
@Setter
@ToString
public class User {
    private long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date joinedAt;
    private String userId;
    private String hashedPw;
    private String name;
    private String email;


}
