package com.kakao.cafe.member.dto;

import com.kakao.cafe.member.domain.Member;
import lombok.Getter;

@Getter
public class MemberResponseDTO {

    private Long id;
    private String email;
    private String nickName;
    private String createDate;

    public MemberResponseDTO(Member member) {
        this.id = member.getId();
        this.email = member.getEmail();
        this.nickName = member.getNickName();
        this.createDate = member.getCreateDateString();
    }
}
