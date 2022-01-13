package com.kakao.cafe.common;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession();

        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("/");
            return false;
        }

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
