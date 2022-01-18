package com.kakao.cafe.interceptor;

import com.kakao.cafe.domain.user.User;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    public static final List<String> loginNeeded = List.of("/posts/**", "/write");
    public static final List<String> loginNotNeeded = List.of("/posts");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User loginUser = (User) request.getSession().getAttribute("loginUser");

        if (loginUser != null) {
            return true;
        }

        response.sendRedirect("/");
        return false;
    }
}
