package com.kakao.cafe.service.member;

import com.kakao.cafe.domain.member.Member;

import java.util.List;
import java.util.Optional;

public interface MemberService {

    List<Member> findAllMembers();

    void saveMember(Member member);

    Optional<Member> findOne(Long memberId);

    void editMemberInformation(Long memberId, Member member);

    void deleteMember(Long memberId);

    void deleteAllMembers();
}
