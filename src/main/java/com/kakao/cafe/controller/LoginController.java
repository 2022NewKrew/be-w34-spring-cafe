package com.kakao.cafe.controller;

import com.kakao.cafe.Service.LoginService;
import com.kakao.cafe.model.Login.LoginAuthDto;
import com.kakao.cafe.model.Login.LoginRequestDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    Logger logger = LoggerFactory.getLogger(UserController.class);
    private final LoginService loginService;

    @GetMapping
    public String loginPage() {
        return "user/login";
    }

    @PostMapping
    public String login(LoginRequestDto loginRequestDto, HttpSession session) {

        logger.info("[POST] 로그인 정보 입력");

        try {
            LoginAuthDto authInfo = loginService.authenticate(loginRequestDto);
            session.setAttribute("authInfo", authInfo);
            return "redirect:/";
        } catch (IllegalArgumentException e) {
            return "user/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        logger.info("[GET] 로그아웃");
        session.invalidate();
        return "redirect:/";
    }
}
