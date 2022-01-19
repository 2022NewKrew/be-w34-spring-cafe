package com.kakao.cafe.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        Object userId = request.getSession().getAttribute("user-id");
        if(userId == null) {
            log.error("인증되지 않은 사용자입니다");
            return false;
        }
        return true;
    }
}
