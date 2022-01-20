package com.kakao.cafe.adapter.in.presentation.common;

import com.kakao.cafe.application.user.dto.UserInfo;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

public class SessionCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws Exception {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("sessionedUser") != null) {
            UserInfo sessionedUser = (UserInfo) session.getAttribute("sessionedUser");
            request.setAttribute("sessionedUser", sessionedUser);
            return true;
        }
        response.sendRedirect(request.getContextPath() + "/user/login");
        return false;
    }
}
