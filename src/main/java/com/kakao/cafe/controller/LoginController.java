package com.kakao.cafe.controller;

import com.kakao.cafe.controller.error.ErrorMessageBox;
import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.login.LoginService;
import com.kakao.cafe.core.SessionConst;
import com.kakao.cafe.domain.login.dto.UserLogin;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Arrays;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class LoginController {

    @Autowired
    private final LoginService loginService;

    @GetMapping("/login")
    public String loginPage() {
        return "user/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UserLogin userLogin, HttpSession httpSession, Model model) {
        try {
            User user = loginService.login(userLogin);
            httpSession.setAttribute(SessionConst.LOGIN_COOKIE, user);
            return "redirect:/";
        } catch (Exception e) {
            return ErrorMessageBox.handling("아이디나 비밀번호가 일치하지 않습니다.", model);
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {
        httpSession.invalidate();
        return "redirect:/";
    }
}
