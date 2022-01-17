package com.kakao.cafe.service.member;

import com.kakao.cafe.domain.member.Member;
import com.kakao.cafe.domain.member.UserId;
import com.kakao.cafe.exception.ErrorMessages;
import com.kakao.cafe.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class MemberServiceV1 implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public List<Member> inquireAllMembers() {
        return memberRepository.findAllMembers();
    }

    @Override
    public Member joinMember(Member member) {
        if (member.getMemberId() != null)
            return editMemberInformation(member);
        return memberRepository.saveMember(member);
    }

    @Override
    @Transactional(readOnly = true)
    public Member inquireOneMember(Long memberId) {
        return memberRepository.findOne(memberId);
    }

    @Override
    public Member editMemberInformation(Member member) {
        if (!member.getPassword().equals(memberRepository.findOne(member.getMemberId()).getPassword()))
            throw new IllegalArgumentException(ErrorMessages.WRONG_PASSWORD);
        return memberRepository.updateByMemberId(member);
    }

    @Override
    public void deleteMember(Long memberId) {
        memberRepository.deleteMember(memberId);
    }

    @Override
    @Transactional(readOnly = true)
    public Member inquireMemberByUserId(UserId userId) {
        return memberRepository.findByUserId(userId);
    }

    @Override
    public void deleteAllMembers() {
        memberRepository.deleteAllMembers();
    }

    @Override
    public Member loginMember(String userId, String password) {
        Long memberId = memberRepository.isUserIdExist(new UserId(userId));
        if (memberId == null) {
            throw new IllegalArgumentException(ErrorMessages.LOGIN_FAILED);
        }
        Member member = memberRepository.findOne(memberId);
        if (!member.getPassword().getPassword().equals(password)) {
            throw new IllegalArgumentException(ErrorMessages.LOGIN_FAILED);
        }
        return member;
    }
}
