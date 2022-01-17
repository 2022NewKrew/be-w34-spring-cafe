package com.kakao.cafe.controller;

import com.kakao.cafe.util.SessionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final HttpSession session;

    @GetMapping("/login")
    public ModelAndView loginView(HttpServletRequest request, ModelAndView mav) {
        String referer = request.getHeader("Referer");
        if (isRefererNeedToBeRegisteredForSession(referer)) {
            SessionUtil.setAfterLoginRedirectedUrl(session, referer);
        }

        mav.setViewName("login");

        return mav;
    }

    private boolean isRefererNeedToBeRegisteredForSession(String referer) {
        return referer != null && !referer.contains("/login");
    }
}
