package com.kakao.cafe.controller;

import com.kakao.cafe.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/account")
@Log4j2
@RequiredArgsConstructor
public class AccountController {
    private final UserService userService;

    @GetMapping("/login")
    public String login() {
        return "user/login";
    }

    @PostMapping("/login")
    public String loginRequest() {
        return "redirect:/";
    }

    @GetMapping("/signup")
    public String signup() {
        return "user/signup";
    }

    @PostMapping("/signup")
    public String signupRequest() {
        return "user/signup_success";
    }

    @GetMapping("/mypage/edit")
    public String editMyAccount() {
        return "todo"; // TODO
    }

    @PutMapping("/mypage/edit")
    public String editMyAccountRequest() {
        return "todo"; // TODO
    }

    @GetMapping("/mypage/delete")
    public String deleteMyAccount() {
        return "todo"; // TODO
    }

    @DeleteMapping("/mypage/edit")
    public String deleteMyAccountRequest() {
        return "todo"; // TODO
    }

}
