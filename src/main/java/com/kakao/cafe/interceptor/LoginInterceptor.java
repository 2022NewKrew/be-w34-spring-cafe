package com.kakao.cafe.interceptor;

import com.kakao.cafe.controller.UserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {
    private static final Logger log = LoggerFactory.getLogger(LoginInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("LoginInterceptor start");
        HttpSession session = request.getSession();
        log.info(request.getRequestURL().toString());
        if (session.getAttribute("sessionedUser") == null) {
            log.info("LoginInterceptor detect no session");
            response.sendRedirect("/user/login");
            return false;
        }
        return true;
    }
}
