package com.kakao.cafe.web.login;

import com.kakao.cafe.domain.login.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;



/**
 * To-do
 */
@Controller
public class LoginController {
    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/user/login")
    public String loginForm(@ModelAttribute LoginForm loginForm) {
        return "user/login";
    }

}
