package com.kakao.cafe.controller;

import com.kakao.cafe.domain.member.*;
import com.kakao.cafe.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/users")
    public String membersList(Model model) {
        List<Member> members = memberService.findAllMembers();
        model.addAttribute("members", members);
        return "user/user-list";
    }

    @PostMapping("/users/create")
    public String joinMember(Model model, UserId userId, Name name, Password password, Email email) {
        log.info("{} is joined", userId);
        Member member = new Member(userId, name, password, email);
        memberService.saveMember(member);
        model.addAttribute("member", member);
        return "redirect:/users";
    }

    @GetMapping("/users/{memberId}")
    public String inquireMemberProfile(@PathVariable("memberId") Long memberId, Model model) {
        Optional<Member> member = memberService.findOne(memberId);
        if (member.isPresent()) {
            log.info("{} information inquire", member.get().getUserId());
            model.addAttribute("member", member.get());
            return "user/user-profile";
        }
        log.info("request member is not present");
        return "redirect:/users";
    }
}
