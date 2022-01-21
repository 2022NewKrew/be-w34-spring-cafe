package com.kakao.cafe.interceptor;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.exception.UnauthorizedException;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Component
public class AuthorizationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        PersonalAuthorizationSecured personalAuthorizationSecured = handlerMethod.getMethodAnnotation(PersonalAuthorizationSecured.class);

        if (personalAuthorizationSecured == null) {
            return true;
        }

        User sessionedUser = (User) request.getSession().getAttribute("sessionedUser");
        // 본인 인가 확인
        int userPk = Integer.parseInt(Optional.ofNullable(request.getParameter("userPk")).orElse("0"));
        if (userPk != sessionedUser.getId()) {
            throw new UnauthorizedException();
        }

        return true;
    }

}
