package com.kakao.cafe.home.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    @GetMapping("/signup")
    public String signUp() {
        return "user/form";
    }

    @GetMapping("/login")
    public String login() {
        return "user/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/login_failed")
    public String loginFailed() {
        return "user/login_failed";
    }

    @GetMapping("/profile")
    public String profile(Model model, HttpSession session) {
        if (session.getAttribute("sessionedUser") == null) {
            return "redirect:/";
        }
        return "user/profile";
    }

    @GetMapping("/question")
    public String question(HttpSession session) {
        if (session.getAttribute("sessionedUser") == null) {
            return "redirect:/";
        }
        return "qna/form";
    }
}
