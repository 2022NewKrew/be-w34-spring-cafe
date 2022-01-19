package com.kakao.cafe.handler;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    private static final String SESSION = "sessionUser";
    public static final List<String> includePatterns = List.of("/post/write", "/posts/**");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getSession().getAttribute(SESSION) == null) {
            response.sendRedirect(request.getContextPath() + "/user/login");
            return false;
        }

        return true;
    }
}
