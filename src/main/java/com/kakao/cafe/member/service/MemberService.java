package com.kakao.cafe.member.service;

import com.kakao.cafe.member.domain.Member;
import com.kakao.cafe.member.dto.MemberRequestDTO;
import com.kakao.cafe.member.dto.MemberResponseDTO;
import com.kakao.cafe.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Long join(MemberRequestDTO memberRequestDTO) {
        validateDuplicateEmail(memberRequestDTO.getEmail());
        Member member = memberRequestDTO.toMember(LocalDate.now());
        validateCheckPassword(member, memberRequestDTO.getPasswordCheck());
        return memberRepository.save(member);
    }

    private void validateDuplicateEmail(String email) {
        if (memberRepository.findByEmail(email) != null) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }
    }

    private void validateCheckPassword(Member member, String passwordCheck) {
        if (!member.checkPassword(passwordCheck)) {
            throw new IllegalArgumentException("패스워드 확인이 일치하지 않습니다.");
        }
    }

    public MemberResponseDTO findOne(Long id) {
        return new MemberResponseDTO(memberRepository.findOne(id));
    }

    public List<MemberResponseDTO> findAll() {
        return memberRepository.findAll().stream()
                .map(MemberResponseDTO::new)
                .collect(Collectors.toList());
    }

    public void update(Long id) {
        // memberRepository.update(id);
    }
}
