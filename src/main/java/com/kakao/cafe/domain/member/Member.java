package com.kakao.cafe.domain.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class Member {

    private final UserId userId;
    private final Name name;
    private final Password password;
    private final Email email;
    private Long memberId;

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return Objects.equals(userId, member.userId) && Objects.equals(name, member.name) && Objects.equals(password, member.password) && Objects.equals(email, member.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, name, password, email, memberId);
    }
}
