package com.kakao.cafe.controller;

import com.kakao.cafe.dto.AuthDto;
import com.kakao.cafe.dto.UserDto;
import com.kakao.cafe.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/account")
@Log4j2
@RequiredArgsConstructor
public class AccountController {
    private final UserService userService;

    @GetMapping("/login")
    public String loginForm() {
        return "user/login";
    }

    @PostMapping("/login")
    public String login(UserDto userDto, HttpSession session) {
        AuthDto authDto = userService.login(userDto);
        if (authDto == null) {
            return "redirect:/account/login";
        }
        session.setAttribute("auth", authDto);
        return "redirect:/";
    }

    @GetMapping("/signup")
    public String signupForm() {
        return "user/signup";
    }

    @PostMapping("/signup")
    public String signup(UserDto userDto, Model model) {
        log.info("[signup] user: " + userDto);
        model.addAttribute("user", userService.register(userDto));
        return "user/signup_success";
    }

    @GetMapping("/mypage/edit")
    public String editMyAccountForm() {
        return "todo"; // TODO
    }

    @PutMapping("/mypage/edit")
    public String editMyAccount() {
        return "todo"; // TODO
    }

    @GetMapping("/mypage/delete")
    public String deleteMyAccountForm() {
        return "todo"; // TODO
    }

    @DeleteMapping("/mypage/edit")
    public String deleteMyAccount() {
        return "todo"; // TODO
    }

}
