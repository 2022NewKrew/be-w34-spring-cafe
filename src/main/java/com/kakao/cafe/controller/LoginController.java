package com.kakao.cafe.controller;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.controller.dto.LoginRequest;
import com.kakao.cafe.service.LoginService;
import com.kakao.cafe.web.meta.SessionConst;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/login")
    public String login(@ModelAttribute LoginRequest loginRequest, HttpSession session) {
        User user = loginService.login(loginRequest);
        session.setAttribute(SessionConst.LOGIN_USER, user.getId());

        return "redirect:/";
    }
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }
}
