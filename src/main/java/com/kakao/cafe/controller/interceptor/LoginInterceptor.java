package com.kakao.cafe.controller.interceptor;

import com.kakao.cafe.error.exception.LoginException;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    private static final String SESSION_USER = "sessionUser";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        if (ObjectUtils.isEmpty(((HandlerMethod)handler).getMethodAnnotation(ValidateLogin.class))) {
            return true;
        }

        if (ObjectUtils.isEmpty(request.getSession().getAttribute(SESSION_USER).toString())) {
            throw new LoginException();
        }

        return true;
    }
}

