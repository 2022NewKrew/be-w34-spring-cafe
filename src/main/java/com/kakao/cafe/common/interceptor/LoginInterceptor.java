package com.kakao.cafe.common.interceptor;

import com.kakao.cafe.common.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
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
            throw new BaseException("로그인이 필요한 서비스입니다.");
        }

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
