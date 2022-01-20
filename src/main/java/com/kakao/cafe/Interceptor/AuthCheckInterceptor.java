package com.kakao.cafe.Interceptor;

import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class AuthCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (HttpMethod.GET.matches(request.getMethod())) {
            return true;
        }

        HttpSession session = request.getSession(false);
        if (session != null) {
            Object sessionId = session.getAttribute("sessionId");
            if (sessionId != null) {
                return true;
            }
        }
        response.sendRedirect("/login");
        return false;
    }
}
