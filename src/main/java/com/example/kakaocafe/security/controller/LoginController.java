package com.example.kakaocafe.security.controller;

import com.example.kakaocafe.core.meta.SessionData;
import com.example.kakaocafe.core.meta.URLPath;
import com.example.kakaocafe.domain.user.User;
import com.example.kakaocafe.security.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping(path = "/login")
    public ModelAndView login(@RequestParam("email") String email,
                              @RequestParam("password") String password,
                              HttpSession httpSession) throws LoginException {

        final boolean hasNotUserKey = httpSession.getAttribute(SessionData.USER_KEY) == null;

        if (hasNotUserKey) {
            final User user = loginService.login(email, password);
            httpSession.setAttribute(SessionData.USER_KEY, user.getId());
            httpSession.setAttribute(SessionData.USER_NAME, user.getName());
        }

        return new ModelAndView(URLPath.INDEX.getRedirectPath());
    }

    @GetMapping(path = "/logout")
    public ModelAndView logout(HttpSession httpSession) {
        httpSession.invalidate();
        return new ModelAndView(URLPath.INDEX.getRedirectPath());
    }
}
