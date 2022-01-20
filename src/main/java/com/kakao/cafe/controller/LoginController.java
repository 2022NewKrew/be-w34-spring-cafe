package com.kakao.cafe.controller;

import com.kakao.cafe.model.user.UserLoginRequest;
import com.kakao.cafe.service.user.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@Controller
public class LoginController {
    private static final String ATTRIBUTE_NAME_OF_CURRENT_USER = "currentUser";

    private final LoginService loginService;

    @PostMapping("/login")
    public String login(@Valid UserLoginRequest request, HttpSession session, RedirectAttributes rttr) {
        session.setAttribute(ATTRIBUTE_NAME_OF_CURRENT_USER, loginService.login(request.getEmail(), request.getPassword()));
        rttr.addFlashAttribute("msg", "로그인에 성공하였습니다.");
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session, RedirectAttributes rttr) {
        session.invalidate();
        rttr.addFlashAttribute("msg", "로그아웃에 성공하였습니다.");
        return "redirect:/";
    }

}
