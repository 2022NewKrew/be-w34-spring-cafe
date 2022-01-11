package com.kakao.cafe.member.dto;

import com.kakao.cafe.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class MemberRequestDTO {

    private String email;
    private String nickName;
    private String password;
    private String passwordCheck;

    public Member toMember(LocalDate createDate) {
        return new Member(email, nickName, password, createDate);
    }
}
