package com.kakao.cafe.interceptor;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.exception.UnauthenticatedException;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        AuthenticationSecured authenticationSecured = handlerMethod.getMethodAnnotation(AuthenticationSecured.class);

        if (authenticationSecured == null) {
            return true;
        }

        User sessionedUser = (User) request.getSession().getAttribute("sessionedUser");
        if (sessionedUser == null) {
            throw new UnauthenticatedException();
        }

        return true;
    }
}
