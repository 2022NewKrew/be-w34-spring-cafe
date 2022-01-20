package com.kakao.cafe.interceptor;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.exception.UnauthorizedException;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Component
public class AuthorizationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User sessionedUser = (User) request.getSession().getAttribute("sessionedUser");
        // 본인 인가 확인
        Integer userPk = Integer.valueOf(Optional.ofNullable(request.getParameter("userPk")).orElse("0"));
        if (!userPk.equals(sessionedUser.getId())) {
            throw new UnauthorizedException();
        }

        return true;
    }

}
