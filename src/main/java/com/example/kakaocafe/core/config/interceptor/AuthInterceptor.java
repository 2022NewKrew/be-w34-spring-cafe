package com.example.kakaocafe.core.config.interceptor;

import com.example.kakaocafe.core.meta.SessionAttributes;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        final HttpSession httpSession = request.getSession(false);

        if (httpSession == null) {
            response.sendRedirect("/login");
            return false;
        }

        if (httpSession.getAttribute(SessionAttributes.USER_KEY.getValue()) == null) {
            response.sendRedirect("/login");
            return false;
        }

        return true;
    }
}
