package com.kakao.cafe.member.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Builder
@ToString
public class Member {

    private Long id;
    private String email;
    private String nickname;
    private String password;
    private LocalDate createDate;
  
    public void setId(Long id) {
        this.id = id;
    }
}
