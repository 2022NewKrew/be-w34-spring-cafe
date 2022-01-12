package com.kakao.cafe.member.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class Member {

    private Long id;
    private String email;
    private String nickName;
    private String password;
    private LocalDate createDate;
  
    public void setId(Long id) {
        this.id = id;
    }
}
