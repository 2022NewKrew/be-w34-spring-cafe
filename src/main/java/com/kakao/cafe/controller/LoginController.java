package com.kakao.cafe.controller;

import com.kakao.cafe.core.meta.SessionData;
import com.kakao.cafe.domain.user.LoginService;
import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.dto.UserLoginDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/login")
    public String initLoginForm() {
        return "/user/login_form";
    }

    @PostMapping("/login")
    public String proceedLoginForm(HttpSession session,
                                   @ModelAttribute UserLoginDto dto) {
        final User user = loginService.login(dto);
        session.setAttribute(SessionData.USER_KEY, user.getId());
        session.setAttribute(SessionData.USER_NAME, user.getNickname());
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String doLogout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
