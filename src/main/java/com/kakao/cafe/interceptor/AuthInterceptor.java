package com.kakao.cafe.interceptor;

import com.kakao.cafe.annotation.Auth;
import com.kakao.cafe.dto.user.UserSessionDto;
import com.kakao.cafe.exception.NullSessionException;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (handler instanceof ResourceHttpRequestHandler || !hasAnnotation((HandlerMethod) handler)) {
            return true;
        }
        HttpSession session = request.getSession();
        UserSessionDto sessionedUser = (UserSessionDto) session.getAttribute("sessionedUser");
        if (sessionedUser == null) {
            throw new NullSessionException("로그인 되어있지 않습니다.");
        }
        return true;
    }

    private boolean hasAnnotation(HandlerMethod handlerMethod) {
        return handlerMethod.hasMethodAnnotation(Auth.class);
    }
}
