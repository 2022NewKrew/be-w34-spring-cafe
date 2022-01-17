package com.example.kakaocafe.controller;

import com.example.kakaocafe.core.meta.SessionData;
import com.example.kakaocafe.core.meta.URLPath;
import com.example.kakaocafe.core.meta.ViewPath;
import com.example.kakaocafe.domain.user.User;
import com.example.kakaocafe.domain.user.LoginService;
import com.example.kakaocafe.domain.user.dto.LoginForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @GetMapping(path = "/login")
    public ModelAndView showLoginForm(@SessionAttribute(value = "userKey", required = false) Long userKey,
                                      Model model) {

        if (userKey != null) {
            return new ModelAndView(URLPath.INDEX.getRedirectPath());
        }

        return new ModelAndView(ViewPath.LOGIN)
                .addAllObjects(model.asMap());
    }

    @PostMapping(path = "/users/login")
    public ModelAndView login(@ModelAttribute LoginForm loginForm,
                              HttpSession httpSession) throws LoginException {

        final Long userKey = ((Long) httpSession.getAttribute(SessionData.USER_KEY));

        if (userKey == null) {
            final User user = loginService.login(loginForm);
            httpSession.setAttribute(SessionData.USER_KEY, user.getId());
            httpSession.setAttribute(SessionData.USER_NAME, user.getName());
        }

        return new ModelAndView(URLPath.INDEX.getRedirectPath());
    }

    @GetMapping(path = "/users/logout")
    public ModelAndView logout(HttpSession httpSession) {
        httpSession.invalidate();
        return new ModelAndView(URLPath.INDEX.getRedirectPath());
    }
}

//    아래 방법도 가능
//    @GetMapping
//    public ModelAndView showLoginForm(HttpServletRequest request) {
//
//        final ModelAndView modelAndView = new ModelAndView(ViewPath.LOGIN);
//
//        final Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
//        if (flashMap != null) {
//            modelAndView.addObject("isFailed", flashMap.get("isFailed"));
//        }
//
//        return modelAndView;
//    }
