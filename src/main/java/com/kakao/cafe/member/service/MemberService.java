package com.kakao.cafe.member.service;

import com.kakao.cafe.member.domain.Member;
import com.kakao.cafe.member.dto.MemberRequestDTO;
import com.kakao.cafe.member.dto.MemberResponseDTO;
import com.kakao.cafe.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Long join(MemberRequestDTO memberRequestDTO) {
        validateDuplicateUserId(memberRequestDTO.getUserId());
        validateCheckPassword(memberRequestDTO);
        Member member = memberRequestDTO.toMember();
        return memberRepository.save(member);
    }

    private void validateDuplicateUserId(String userId) {
        if (memberRepository.findByUserId(userId) != null) {
            throw new IllegalArgumentException("이미 존재하는 유저 아이디입니다.");
        }
    }

    private void validateCheckPassword(MemberRequestDTO memberRequestDTO) {
        if (memberRequestDTO.checkPassword()) {
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
