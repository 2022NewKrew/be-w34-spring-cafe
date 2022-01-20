package com.kakao.cafe.config;

import com.kakao.cafe.exception.SessionUserNotFoundException;
import com.kakao.cafe.home.dto.SessionUser;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionUserInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Authorized typeAnnotation = handlerMethod.getBeanType().getAnnotation(Authorized.class);
        Authorized methodAnnotation = handlerMethod.getMethod().getAnnotation(Authorized.class);

        if (typeAnnotation == null && methodAnnotation == null) {
            return true;
        }

        HttpSession session = request.getSession();
        Object sessionUser = session.getAttribute("sessionUser");
        if (!(sessionUser instanceof SessionUser)) {
            throw new SessionUserNotFoundException();
        }
        return true;
    }

}
