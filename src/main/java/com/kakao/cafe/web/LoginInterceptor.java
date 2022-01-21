package com.kakao.cafe.web;

import com.kakao.cafe.domain.entity.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("sessionedUser");
        if (user == null) {
            response.sendRedirect("/users/login");
            return false;
        }

        request.setAttribute("sessionedUser", user);
        return true;
    }
}
