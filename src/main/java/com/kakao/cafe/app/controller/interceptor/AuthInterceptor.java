package com.kakao.cafe.app.controller.interceptor;

import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Set;

public class AuthInterceptor implements HandlerInterceptor {

    private final Set<HttpMethod> methods;

    public AuthInterceptor(HttpMethod... methods) {
        this.methods = Set.of(methods);
    }

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler
    ) throws IOException {
        if (!methods.contains(HttpMethod.resolve(request.getMethod()))) {
            return true;
        }
        HttpSession session = request.getSession();
        if (session.getAttribute("currentUserId") == null) {
            response.sendRedirect("/users/login");
            return false;
        }
        return true;
    }
}
