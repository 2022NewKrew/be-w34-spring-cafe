package com.kakao.cafe.common.config.interceptor;

import com.kakao.cafe.user.dto.response.UserResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws Exception {
        UserResponse sessionUser = (UserResponse) request.getSession().getAttribute("sessionUser");
        if (sessionUser != null) {
            return true;
        }

        request.getSession().setAttribute("destUrl", getDestUrl(request));
        response.sendRedirect("/users/login-form");
        return false;
    }

    private String getDestUrl(HttpServletRequest request) {
        String destUri = request.getRequestURI();
        String destQuery = request.getQueryString();
        if (destQuery == null) {
            return destUri;
        }
        return destUri + "?" + destQuery;
    }
}
