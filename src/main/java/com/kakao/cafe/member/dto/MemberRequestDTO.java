package com.kakao.cafe.member.dto;

import com.kakao.cafe.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@ToString
public class MemberRequestDTO {

    private String email;
    private String nickname;
    private String password;
    private String passwordCheck;

    public Member toMember(LocalDate createDate) {
        return Member.builder()
                .email(email)
                .nickname(nickname)
                .password(password)
                .createDate(createDate)
                .build();
    }
}
