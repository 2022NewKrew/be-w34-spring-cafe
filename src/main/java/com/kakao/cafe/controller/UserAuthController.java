package com.kakao.cafe.controller;

import com.kakao.cafe.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@RequestMapping("auth")
public class UserAuthController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;


    @PostMapping("login")
    public String login(@ModelAttribute UserAuthDto userAuthDto, HttpSession httpSession, Model model) {
        try {
            int id = userService.login(userAuthDto);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "/user/login";
        }
        httpSession.setAttribute("sessionId", userAuthDto.getUserId());
        logger.info("POST /users/login: {}", userAuthDto);
        return "redirect:/users";
    }

    @GetMapping("logout")
    public String logout(HttpSession httpSession) {
        httpSession.invalidate();
        logger.info("GET /users/logout");
        return "redirect:/";
    }
}
