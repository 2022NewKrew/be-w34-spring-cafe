package com.kakao.cafe.interceptor;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.exception.UnauthenticatedException;
import com.kakao.cafe.exception.UnauthorizedException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User sessionedUser = (User) request.getSession().getAttribute("sessionedUser");
        if (sessionedUser == null) {
            throw new UnauthenticatedException();
        }

        return true;
    }
}
