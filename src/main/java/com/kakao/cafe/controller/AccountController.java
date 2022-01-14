package com.kakao.cafe.controller;

import com.kakao.cafe.dto.AuthDto;
import com.kakao.cafe.dto.EditUserDto;
import com.kakao.cafe.dto.UserDto;
import com.kakao.cafe.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/accounts")
@Slf4j
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

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/mypage")
    public String myPage(HttpSession session, Model model) {
        AuthDto authDto = (AuthDto) session.getAttribute("auth");
        if (authDto == null) {
            return "redirect:/accounts/login";
        }
        model.addAttribute("user", userService.getUser(authDto));
        return "user/mypage";
    }

    @GetMapping("/mypage/edit")
    public String editMyPageForm(HttpSession session, Model model) {
        AuthDto authDto = (AuthDto) session.getAttribute("auth");
        if (authDto == null) {
            return "redirect:/accounts/login";
        }
        model.addAttribute("user", userService.getUser(authDto));
        return "user/mypage_edit";
    }

    @PostMapping("/mypage/edit")
    public String editMyPage(HttpSession session, EditUserDto editUserDto) {
        AuthDto authDto = (AuthDto) session.getAttribute("auth");
        if (authDto == null) {
            return "redirect:/accounts/login";
        }
        log.info("accounts/mypage/edit" + editUserDto);
        userService.modify(editUserDto);
        return "redirect:/accounts/mypage";
    }

    @GetMapping("/delete")
    public String deleteMyAccountForm(HttpSession session) {
        AuthDto authDto = (AuthDto) session.getAttribute("auth");
        if (authDto == null) {
            return "redirect:/accounts/login";
        }
        return "user/delete";
    }

    @DeleteMapping("/delete")
    public String deleteMyAccount(HttpSession session) {
        AuthDto authDto = (AuthDto) session.getAttribute("auth");
        if (authDto == null) {
            return "redirect:/accounts/login";
        }
        return "redirect:/";
    }

}
