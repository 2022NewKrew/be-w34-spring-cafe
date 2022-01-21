package com.kakao.cafe.controller.interceptor;

import com.kakao.cafe.error.exception.LoginException;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.kakao.cafe.Constant.SESSION_USER;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        if (ObjectUtils.isEmpty(((HandlerMethod)handler).getMethodAnnotation(LoginRequired.class))) {
            return true;
        }

        if (ObjectUtils.isEmpty(request.getSession().getAttribute(SESSION_USER))) {
            throw new LoginException();
        }

        return true;
    }
}

