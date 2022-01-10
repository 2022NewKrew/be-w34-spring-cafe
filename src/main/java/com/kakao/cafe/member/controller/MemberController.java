package com.kakao.cafe.member.controller;

import com.kakao.cafe.member.dto.MemberRequestDTO;
import com.kakao.cafe.member.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {

    private final MemberService memberService;
    private final Logger logger = LoggerFactory.getLogger(MemberController.class);

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/member")
    public String getMemberForm() {
        return "members/form";
    }

    @GetMapping("/members")
    public String getMembers(Model model) {
        model.addAttribute("members", memberService.findAll());
        return "members/list";
    }

    @PostMapping("/members")
    public String postMembers(MemberRequestDTO memberRequestDTO) {
        logger.info("MemberRequestDTO : {}", memberRequestDTO);
        memberService.join(memberRequestDTO);
        return "redirect:members";
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
