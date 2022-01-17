package com.kakao.cafe.common.interceptor;

import com.kakao.cafe.common.auth.Auth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession();

        if (session == null || session.getAttribute("loginUser") == null) {
            response.sendRedirect("/");
            return false;
        }

        log.debug(session.getAttribute("loginUser").toString());

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
