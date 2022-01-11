package com.kakao.cafe.member.domain;

import lombok.Getter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
public class Member {

    private Long id;
    private String email;
    private String nickName;
    private String password;
    private LocalDate createDate;

    public Member(String email, String nickName, String password, LocalDate createDate) {
        this.email = email;
        this.nickName = nickName;
        this.password = password;
        this.createDate = createDate;
    }

    public String getCreateDateString() {
        return createDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean checkPassword(String passwordCheck) {
        return password.equals(passwordCheck);
    }
}
