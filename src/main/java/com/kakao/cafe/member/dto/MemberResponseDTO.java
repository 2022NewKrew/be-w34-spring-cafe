package com.kakao.cafe.member.dto;

import com.kakao.cafe.member.domain.Member;
import lombok.Getter;

import java.time.format.DateTimeFormatter;

@Getter
public class MemberResponseDTO {

    private Long id;
    private String email;
    private String nickname;
    private String createDate;

    public MemberResponseDTO(Member member) {
        this.id = member.getId();
        this.email = member.getEmail();
        this.nickname = member.getNickname();
        this.createDate = member.getCreateDate().format(DateTimeFormatter.ISO_LOCAL_DATE);
    }
}
