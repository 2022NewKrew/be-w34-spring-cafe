package com.kakao.cafe.util.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {

    private final String LOGIN_USER = "loginUser";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        log.info("Request URI: {}, session: {}", request.getRequestURI(), session.getAttribute("loginUser"));
        if (session.getAttribute(LOGIN_USER) == null) {
            response.sendRedirect("/login/form");
            return false;
        }
        return true;
    }
}
