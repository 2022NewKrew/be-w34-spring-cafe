package com.kakao.cafe.handler;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthHandler implements HandlerInterceptor {

    private static final String SESSION = "auth";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean loggedIn = request.getSession().getAttribute(SESSION) == null;
        if (loggedIn) {
            response.sendRedirect(request.getContextPath() + "/users");
            return false;
        }
        return true;
    }
}
