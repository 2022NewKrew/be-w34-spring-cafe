package com.kakao.cafe.service;

import com.kakao.cafe.domain.Member;
import com.kakao.cafe.dto.MemberCreateDto;
import com.kakao.cafe.dto.MemberDto;
import com.kakao.cafe.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    @Autowired
    public MemberServiceImpl(@Qualifier("memberRepositoryJDBC") MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void save(MemberCreateDto memberCreateDto) {
        Member newMember = Member.of(memberCreateDto);

        // 아이디 중복 체크
        if (memberRepository.findByUserId(newMember.getUserId()) != null)
            throw new IllegalArgumentException("member id duplicate");

        // 이메일 중복 체크
        if (memberRepository.findByEmail(newMember.getEmail()) != null)
            throw new IllegalArgumentException("member email duplicate");

        memberRepository.save(newMember);
    }

    @Override
    public MemberDto getMember(String userId) {
        return MemberDto.of(memberRepository.findByUserId(userId));
    }

    @Override
    public void login(String userId, String password, HttpSession session) {
        Member member = memberRepository.findByUserId(userId);
        if (member == null)
            throw new IllegalArgumentException("member not found");

        if (!password.equals(member.getPassword()))
            throw new IllegalArgumentException("member not found, password");

        session.setAttribute("sessionedUser", member);
    }

    @Override
    public List<MemberDto> getMemberList() {
        Optional<List<Member>> userList = memberRepository.findAll();

        return userList.map(users -> users
                .stream()
                .map(MemberDto::of)
                .collect(Collectors.toList())).orElse(null);
    }
}
