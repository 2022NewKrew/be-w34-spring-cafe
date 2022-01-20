package com.kakao.cafe.interceptor;

import com.kakao.cafe.dto.user.UserInfoDto;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    public List needLogin = Arrays.asList("/users/**", "/articles/**");
    public List notNeedLogin = Arrays.asList("/articles");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        UserInfoDto user = (UserInfoDto) request.getSession().getAttribute("sessionedUser");
        if (user != null) {
            return true;
        }
        response.sendRedirect("/login");
        return false;
    }
}
