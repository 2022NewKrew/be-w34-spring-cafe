package com.kakao.cafe.web;
import com.kakao.cafe.web.user.dto.UserResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class AuthHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);

        if(session == null) {
            response.sendRedirect("/user/login");
            return false;
        }

        UserResponse userResponse = (UserResponse) session.getAttribute("sessionedUser");
        if(userResponse == null) {
            response.sendRedirect(request.getContextPath() + "/user/login");
            return false;
        }

        request.setAttribute("sessionedUser", userResponse);
        return true;
    }
}
