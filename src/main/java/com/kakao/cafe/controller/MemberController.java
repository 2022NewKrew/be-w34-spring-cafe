package com.kakao.cafe.controller;

import com.kakao.cafe.aop.annotation.AuthCheckAnnotation;
import com.kakao.cafe.aop.annotation.LoginCheckAnnotation;
import com.kakao.cafe.config.Mapper;
import com.kakao.cafe.domain.member.Member;
import com.kakao.cafe.dto.InquireMemberDto;
import com.kakao.cafe.dto.JoinMemberDto;
import com.kakao.cafe.dto.LoginMemberDto;
import com.kakao.cafe.exception.ErrorMessages;
import com.kakao.cafe.exception.NotEnoughFieldException;
import com.kakao.cafe.exception.UnauthorizedException;
import com.kakao.cafe.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
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

    @GetMapping("/users/login")
    public String getLoginForm(Model model) {
        return "user/login-form";
    }

    @PostMapping("/users/create")
    public String joinMember(@Valid JoinMemberDto memberDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new NotEnoughFieldException(ErrorMessages.NOT_ENOUGH_FIELD);
        }
        Member member = convertToEntity(memberDTO);
        memberService.joinMember(member);
        return "redirect:/users";
    }

    @GetMapping("/users/{memberId}")
    @LoginCheckAnnotation
    public String inquireMemberProfile(@PathVariable("memberId") Long memberId, Model model) {
        InquireMemberDto member = convertToDto(memberService.inquireOneMember(memberId));
        log.info("{} information inquire", member.getUserId());
        model.addAttribute("member", member);
        return "user/user-profile";
    }

    @GetMapping("/users/edit/{memberId}")
    @LoginCheckAnnotation
    public String editMemberInformationForm(@PathVariable("memberId") Long memberId, Model model, HttpSession session) {
        Member member = memberService.inquireOneMember(memberId);
        Member loginMember = (Member) session.getAttribute("loginMember");
        if (!member.getMemberId().equals(loginMember.getMemberId()))
            throw new UnauthorizedException(ErrorMessages.NOT_AUTHORIZED_USER);
        InquireMemberDto memberDto = mapper.map(member);
        model.addAttribute("member", memberDto);
        return "user/user-edit";
    }

    @PostMapping("users/edit")
    @AuthCheckAnnotation
    public String editMemberInformation(JoinMemberDto memberDto, HttpSession session) {
        // 비밀번호 확인하는 부분 질문
        Member loginMember = (Member) session.getAttribute("loginMember");
        memberService.loginMember(memberDto.getPassword(), loginMember.getPassword().getPassword());

        Member changedMember = mapper.map(memberDto);
        memberService.editMemberInformation(changedMember, loginMember);
        return "redirect:/users/" + loginMember.getMemberId();
    }

    @PostMapping("/users/login")
    public String login(@Valid LoginMemberDto loginMemberDto, HttpSession session, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new NotEnoughFieldException(ErrorMessages.NOT_ENOUGH_FIELD);
        }
        Member member = memberService.loginMember(loginMemberDto.getUserId(), loginMemberDto.getPassword());
        session.setAttribute("loginMember", member);
        return "redirect:/";
    }

    @GetMapping("/logout")
    @LoginCheckAnnotation
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    private Member convertToEntity(JoinMemberDto memberDTO) {
        return mapper.map(memberDTO);
    }

    private InquireMemberDto convertToDto(Member member) {
        return mapper.map(member);
    }
}
