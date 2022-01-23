package com.kakao.cafe.interceptor;

import lombok.Getter;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

@Getter
public class LoginCheckInterceptor implements HandlerInterceptor {
    private final List<String> checkUrl = Arrays.asList("/questions/**");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Object userId = session.getAttribute("sessionUserId");

        if(userId == null) {
            response.sendRedirect("/users/login");
            return false;
        }
        return true;
    }
}
