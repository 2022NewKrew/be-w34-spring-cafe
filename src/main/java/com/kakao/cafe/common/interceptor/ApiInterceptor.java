package com.kakao.cafe.common.interceptor;

import com.kakao.cafe.controller.common.LoginUser;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ApiInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession();
        LoginUser loginUser = (LoginUser) session.getAttribute("loginUser");

        if (loginUser == null) {
            throw new Exception("로그인이 필요한 서비스입니다.");
        }

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
