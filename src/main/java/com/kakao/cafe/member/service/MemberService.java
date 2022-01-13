package com.kakao.cafe.member.service;

import com.kakao.cafe.member.domain.Member;
import com.kakao.cafe.member.dto.MemberRequestDTO;
import com.kakao.cafe.member.dto.MemberResponseDTO;
import com.kakao.cafe.member.dto.MemberUpdateRequestDTO;
import com.kakao.cafe.member.repository.MemberRepository;
import com.kakao.cafe.member.util.PasswordChecker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    public Long join(MemberRequestDTO memberRequestDTO) {
        validateDuplicateEmail(memberRequestDTO.getEmail());
        Member member = memberRequestDTO.toMember(LocalDate.now());
        validateCheckPassword(memberRequestDTO.getPassword(), memberRequestDTO.getPasswordCheck());
        return memberRepository.save(member).getId();
    }

    private void validateDuplicateEmail(String email) {
        memberRepository.findByEmail(email).ifPresent(member -> {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        });
    }

    private void validateCheckPassword(String password, String passwordCheck) {
        if (!PasswordChecker.checkPassword(password, passwordCheck)) {
            throw new IllegalArgumentException("비밀번호 확인이 일치하지 않습니다.");
        }
    }

    public MemberResponseDTO findOne(Long id) {
        return new MemberResponseDTO(memberRepository.findOne(id).orElseThrow(() -> {
            throw new IllegalArgumentException("해당 유저가 존재하지 않습니다.");
        }));
    }

    public List<MemberResponseDTO> findAll() {
        return memberRepository.findAll().stream()
                .map(MemberResponseDTO::new)
                .collect(Collectors.toList());
    }

    public void update(Long id, MemberUpdateRequestDTO memberUpdateRequestDTO) {
        Member member = memberRepository.findOne(id).orElseThrow(() -> {
            throw new IllegalArgumentException("해당 유저가 존재하지 않습니다.");
        });

        validateCheckPassword(member.getPassword(), memberUpdateRequestDTO.getCurrentPassword());
        validateCheckPassword(memberUpdateRequestDTO.getPassword(), memberUpdateRequestDTO.getPasswordCheck());
        memberRepository.update(id, memberUpdateRequestDTO.toMember(member.getCreateDate()));
    }
}
