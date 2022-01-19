package com.kakao.cafe.controller;

import com.kakao.cafe.dto.MemberCreateDto;
import com.kakao.cafe.dto.MemberDto;
import com.kakao.cafe.dto.MemberListDto;
import com.kakao.cafe.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/member/form")
    public String form() {
        return "member/form";
    }

    @PostMapping("/members")
    public String createMember(MemberCreateDto memberCreateDto) {
        memberService.save(memberCreateDto);
        return "redirect:members";
    }

    @GetMapping("/members")
    public String getMembers(Model model) {
        List<MemberDto> memberDtoList = memberService.getMemberList();
        model.addAttribute("memberListInfo", new MemberListDto(memberDtoList.size(), memberDtoList));
        return "member/list";
    }

    @GetMapping("/members/{userId}")
    public String getMember(@PathVariable String userId, Model model) {
        model.addAttribute("member", memberService.getMember(userId));
        return "member/profile";
    }
}
