package com.kakao.cafe.domain.member;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Member {

    private final UserId userId;
    private final Name name;
    private final Password password;
    private final Email email;
    private Long memberId;

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }
}
