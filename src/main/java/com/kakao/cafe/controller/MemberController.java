package com.kakao.cafe.controller;

import com.kakao.cafe.config.Mapper;
import com.kakao.cafe.domain.member.Member;
import com.kakao.cafe.dto.InquireMemberDto;
import com.kakao.cafe.dto.JoinMemberDTO;
import com.kakao.cafe.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;
    private final Mapper mapper;

    @GetMapping("/users")
    public String membersList(Model model) {

        List<InquireMemberDto> members = memberService.inquireAllMembers()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        model.addAttribute("members", members);
        return "user/user-list";
    }

    @PostMapping("/users/create")
    public String joinMember(@Valid JoinMemberDTO memberDTO) {
        log.info("{} is joined", memberDTO.getUserId());
        Member member = convertToEntity(memberDTO);
        memberService.joinMember(member);
        return "redirect:/users";
    }

    @GetMapping("/users/{memberId}")
    public String inquireMemberProfile(@PathVariable("memberId") @NotNull Long memberId, Model model) {
        InquireMemberDto member = convertToDto(memberService.inquireOneMember(memberId));
        log.info("{} information inquire", member.getUserId());
        model.addAttribute("member", member);
        return "user/user-profile";
    }

    private Member convertToEntity(JoinMemberDTO memberDTO) {
        return mapper.map(memberDTO);
    }

    private InquireMemberDto convertToDto(Member member) {
        return mapper.map(member);
    }
}
