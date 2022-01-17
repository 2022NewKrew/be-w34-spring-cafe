package com.kakao.cafe.service.member;

import com.kakao.cafe.domain.member.Member;
import com.kakao.cafe.domain.member.UserId;

import java.util.List;

public interface MemberService {

    List<Member> inquireAllMembers();

    Member inquireMemberByUserId(UserId userId);

    Member joinMember(Member member);

    Member inquireOneMember(Long memberId);

    Member editMemberInformation(Member member);

    void deleteMember(Long memberId);

    void deleteAllMembers();

    Member loginMember(String userId, String password);
}
