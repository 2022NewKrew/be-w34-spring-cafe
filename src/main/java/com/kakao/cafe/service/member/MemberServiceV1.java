package com.kakao.cafe.service.member;

import com.kakao.cafe.domain.member.Member;
import com.kakao.cafe.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceV1 implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public List<Member> findAllMembers() {
        return memberRepository.findAllMembers();
    }

    @Override
    public void saveMember(Member member) {
        memberRepository.saveMember(member);
    }

    @Override
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }

    @Override
    public void editMemberInformation(Long memberId, Member member) {
        deleteMember(memberId);
        saveMember(member);
    }

    @Override
    public void deleteMember(Long memberId) {
        memberRepository.deleteMember(memberId);
    }

    @Override
    public void deleteAllMembers() {
        memberRepository.deleteAllMembers();
    }
}
