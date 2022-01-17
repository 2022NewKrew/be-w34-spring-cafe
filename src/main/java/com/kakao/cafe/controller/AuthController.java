package com.kakao.cafe.controller;

import com.kakao.cafe.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class AuthController {
    private final MemberService memberService;

    @Autowired
    public AuthController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/login")
    public String login(String userId, String password, HttpSession session) {
        memberService.login(userId, password, session);
        return "redirect:/";
    }

    @GetMapping("/member/logout")
    public String logout(HttpSession session) {
        memberService.logout(session);
        return "redirect:/member/login";
    }
}
