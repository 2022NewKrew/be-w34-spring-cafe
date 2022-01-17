package com.kakao.cafe.controller;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.UserLoginRequest;
import com.kakao.cafe.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.security.sasl.AuthenticationException;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/login")
    public String login(UserLoginRequest userLoginRequest, HttpSession session) {
        try{
            User user = userService.validateUserLogin(userLoginRequest);
            session.setAttribute("sessionedUser", user);
        }
        catch (AuthenticationException e){
            return "/user/login_failed";
        }
        return "redirect:/";
    }
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

}
