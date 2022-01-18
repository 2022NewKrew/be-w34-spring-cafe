package com.kakao.cafe.controller;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.LoginRequest;
import com.kakao.cafe.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("")
@RequiredArgsConstructor
public class LoginController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final LoginService loginService;

    @PostMapping("/login")
    public String login(@ModelAttribute LoginRequest loginRequest, HttpSession session) {
        logger.info("POST:/login {}", loginRequest.getNickname());
        User user = loginService.login(loginRequest);
        session.setAttribute("loginUser", user.getId());

        return "redirect:/";
    }
    @GetMapping("/logout")
    public String logout(HttpSession session){
        logger.info("POST:/logout {}", session.getAttribute("loginUser"));
        session.invalidate();
        return "redirect:/";
    }
}
