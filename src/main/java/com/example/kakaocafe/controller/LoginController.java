package com.example.kakaocafe.controller;

import com.example.kakaocafe.domain.user.dto.LoginForm;
import com.example.kakaocafe.core.meta.URLPath;
import com.example.kakaocafe.domain.user.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping(path = "/login")
    public ModelAndView login(@ModelAttribute LoginForm loginForm) throws LoginException {
        final long userKey = loginService.login(loginForm);

        return new ModelAndView(URLPath.INDEX.getRedirectPath())
                .addObject("userKey", userKey);
    }

    @GetMapping(path = "/logout")
    public ModelAndView logout(HttpSession httpSession) {
        httpSession.invalidate();
        return new ModelAndView(URLPath.INDEX.getRedirectPath());
    }
}
