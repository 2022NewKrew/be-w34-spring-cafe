package com.kakao.cafe.controller.interceptor;

import com.kakao.cafe.domain.user.exception.AnonymousUserException;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;

public class AuthenticatedInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if(!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Class<?> controller = handlerMethod.getBeanType();
        Method method = handlerMethod.getMethod();

        Authenticated authenticated = method.getAnnotation(Authenticated.class);
        Authenticated typeAuthenticated = controller.getAnnotation(Authenticated.class);
        if(authenticated == null && typeAuthenticated == null) {
            return true;
        }
        checkSessionContainsUser(request);
        return true;
    }

    private void checkSessionContainsUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        boolean hasSession = session.getAttribute("sessionUser") != null;
        if(!hasSession) {
            throw new AnonymousUserException("인증된 사용자가 아닙니다.");
        }
    }
}
