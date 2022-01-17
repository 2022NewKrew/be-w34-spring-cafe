package com.kakao.cafe.advice;

import com.kakao.cafe.dto.UserDto;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    public static final List<String> excludePathPatterns = List.of("/", "/auth/login/**", "/auth/signup/**", "/static/**");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        UserDto.UserSessionDto loginId = (UserDto.UserSessionDto) request.getSession().getAttribute("sessionedUser");

        if (loginId == null) {
            response.sendRedirect(request.getContextPath() + "/auth/login/form");
            return false;
        }

        return true;
    }
}
