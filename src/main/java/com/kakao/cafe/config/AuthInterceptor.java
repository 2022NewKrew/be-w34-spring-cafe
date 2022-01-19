package com.kakao.cafe.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        if(request.getMethod().equals("POST") && request.getRequestURI().equals("/users")){
            return true;
        }
        if (session.getAttribute("sessionedUser") == null){
            response.sendRedirect("/user/login.html");
            return false;
        }
        return true;
    }
}
