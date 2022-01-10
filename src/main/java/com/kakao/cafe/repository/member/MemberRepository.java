package com.kakao.cafe.repository.member;

import com.kakao.cafe.domain.member.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    void saveMember(Member member);

    List<Member> findAllMembers();

    Optional<Member> findOne(Long memberId);

    void deleteMember(Long id);

    void deleteAllMembers();
}
