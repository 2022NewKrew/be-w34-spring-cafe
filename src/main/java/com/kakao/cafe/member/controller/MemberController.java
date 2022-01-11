package com.kakao.cafe.member.controller;

import com.kakao.cafe.member.dto.MemberRequestDTO;
import com.kakao.cafe.member.dto.MemberResponseDTO;
import com.kakao.cafe.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/memberForm")
    public String getMemberForm() {
        return "members/joinForm";
    }

    @GetMapping("/members")
    public String getMembers(Model model) {
        List<MemberResponseDTO> members = memberService.findAll();

        model.addAttribute("totalSize", members.size());
        model.addAttribute("members", members);
        return "members/list";
    }

    @PostMapping("/members")
    public String postMembers(MemberRequestDTO memberRequestDTO) {
        Long memberId = memberService.join(memberRequestDTO);
        return "redirect:members/joinSuccess?memberId=" + memberId;
    }

    @GetMapping("/members/joinSuccess")
    public String joinSuccess(@RequestParam Long memberId, Model model) {
        model.addAttribute("member", memberService.findOne(memberId));
        return "members/joinSuccess";
    }

    @GetMapping("/members/{id}")
    public String getMember(@PathVariable Long id, Model model) {
        model.addAttribute("member", memberService.findOne(id));
        return "members/profile";
    }

    @PatchMapping("/members/{id}")
    public String patchMember(@PathVariable Long id) {
        return "redirect:members";
    }

    @GetMapping("/login")
    public String getLogin() {
        return "members/login";
    }
}
