package com.kakao.cafe.interceptor;

import com.kakao.cafe.annotation.CheckAuth;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthInterceptor implements HandlerInterceptor {
    private final static String AUTH_SESSION_NAME = "sessionUser";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!hasCheckAuthAnnotation(handler)) {
            return true;
        }
        HttpSession session = request.getSession();
        Object value = session.getAttribute(AUTH_SESSION_NAME);
        if (value == null) {
            response.sendRedirect("/login");
            return false;
        }
        return true;
    }

    private boolean hasCheckAuthAnnotation(Object handler) {
        if (!(handler instanceof HandlerMethod)) {
            return false;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        CheckAuth checkAuth = handlerMethod.getMethodAnnotation(CheckAuth.class);
        return checkAuth != null;
    }
}
