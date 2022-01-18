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
    public String loginForm(Model model) {
        log.debug("[Get] /account/login");
        return "user/login";
    }

    @PostMapping("/login")
    public String login(UserDto userDto, HttpSession session) {
        log.debug("[Post] /account/login " + userDto);
        AuthDto authDto = userService.login(userDto);
        session.setAttribute("auth", authDto);
        return "redirect:/";
    }

    @GetMapping("/signup")
    public String signupForm() {
        log.debug("[Get] /account/signup");
        return "user/signup";
    }

    @PostMapping("/signup")
    public String signup(UserDto userDto, Model model) {
        log.debug("[Post] /account/signup " + userDto);
        model.addAttribute("user", userService.register(userDto));
        return "user/signup_success";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        log.debug("[Get] /account/logout");
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/mypage")
    public String myPage(HttpSession session, Model model) {
        log.debug("[Get] /account/mypage");
        AuthDto authDto = (AuthDto) session.getAttribute("auth");
        model.addAttribute("user", userService.getUser(authDto));
        return "user/mypage";
    }

    @GetMapping("/mypage/edit")
    public String editMyPageForm(HttpSession session, Model model) {
        log.debug("[Get] /account/mypage/edit");
        AuthDto authDto = (AuthDto) session.getAttribute("auth");
        model.addAttribute("user", userService.getUser(authDto));
        return "user/mypage_edit";
    }

    @PostMapping("/mypage/edit")
    public String editMyPage(HttpSession session, EditUserDto editUserDto) {
        log.debug("[Post] /account/mypage/edit " + editUserDto);
        AuthDto authDto = (AuthDto) session.getAttribute("auth");
        userService.modify(editUserDto);
        return "redirect:/users";
    }

    @GetMapping("/delete")
    public String deleteMyAccountForm(HttpSession session) {
        log.debug("[Get] /account/delete");
        AuthDto authDto = (AuthDto) session.getAttribute("auth");
        return "user/delete";
    }

    @DeleteMapping("/delete")
    public String deleteMyAccount(HttpSession session) {
        log.debug("[Delete] /account/delete");
        AuthDto authDto = (AuthDto) session.getAttribute("auth");
        if (authDto == null) {
            return "redirect:/accounts/login";
        }
        return "redirect:/";
    }

}
