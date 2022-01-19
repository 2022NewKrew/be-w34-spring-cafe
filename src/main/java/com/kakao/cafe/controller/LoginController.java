package com.kakao.cafe.controller;

import com.kakao.cafe.dto.user.SessionUser;
import com.kakao.cafe.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class LoginController {
    private final UserService userService;

    @GetMapping("/login")
    public String serveLoginPage(HttpSession session, HttpServletRequest request) {
        if (request.getHeader("Referer")!=null){
            session.setAttribute("prevUri", request.getHeader("Referer"));
        }
        return "/user/login";
    }

    @PostMapping("/login")
    public String login(String stringId, String password, HttpSession session) {
        SessionUser sessionUser;
        String destUri = "/";
        try {
            sessionUser = userService.login(stringId, password);
        } catch(IllegalArgumentException e){
            return "/user/login_failed";
        }
        session.setAttribute("sessionUser", sessionUser);
        if (session.getAttribute("prevUri")!=null){
            destUri = (String)session.getAttribute("prevUri");
        }
        return "redirect:"+destUri;
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
