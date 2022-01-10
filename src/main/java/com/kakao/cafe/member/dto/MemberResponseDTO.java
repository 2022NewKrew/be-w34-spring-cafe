package com.kakao.cafe.member.dto;

import com.kakao.cafe.member.domain.Member;

public class MemberResponseDTO {

    private Long id;
    private String userId;
    private String name;
    private String email;

    public MemberResponseDTO(Member member) {
        this.id = member.getId();
        this.userId = member.getUserId();
        this.name = member.getName();
        this.email = member.getEmail();
    }
}
