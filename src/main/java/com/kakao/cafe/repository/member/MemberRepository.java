package com.kakao.cafe.repository.member;

import com.kakao.cafe.domain.member.Member;
import com.kakao.cafe.domain.member.UserId;

import java.util.List;

public interface MemberRepository {

    Member saveMember(Member member);

    List<Member> findAllMembers();

    Member findOne(Long memberId);

    void deleteMember(Long memberId);

    void deleteAllMembers();

    Member findByUserId(UserId userId);

    Long isUserIdExist(UserId userId);

    boolean isMemberIdExist(Long memberId);
}
