package com.kakao.cafe.system;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by melodist
 * Date: 2022-01-19 019
 * Time: 오후 7:57
 */
@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();

        log.info("Login Check Interceptor {}", requestURI);
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("sessionedUser") == null) {
            log.info("Forbidden");
            response.sendRedirect("/user/login?redirectURL=" + requestURI);
            return false;
        }

        return true;
    }
}
