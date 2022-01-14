package com.kakao.cafe.service.member;

import com.kakao.cafe.domain.member.Member;
import com.kakao.cafe.domain.member.UserId;
import com.kakao.cafe.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceV1 implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public List<Member> inquireAllMembers() {
        return memberRepository.findAllMembers();
    }

    @Override
    public Member joinMember(Member member) {
        return memberRepository.saveMember(member);
    }

    @Override
    public Member inquireOneMember(Long memberId) {
        return memberRepository.findOne(memberId);
    }

    @Override
    public Member editMemberInformation(Long memberId, Member member) {
        deleteMember(memberId);
        return joinMember(member);
    }

    @Override
    public void deleteMember(Long memberId) {
        memberRepository.deleteMember(memberId);
    }

    @Override
    public Member inquireMemberByUserId(UserId userId) {
        return memberRepository.findByUserId(userId);
    }

    @Override
    public void deleteAllMembers() {
        memberRepository.deleteAllMembers();
    }
}
