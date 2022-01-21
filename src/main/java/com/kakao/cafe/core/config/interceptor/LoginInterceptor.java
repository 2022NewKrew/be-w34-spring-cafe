package com.kakao.cafe.core.config.interceptor;

import com.kakao.cafe.core.meta.SessionData;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

public class LoginInterceptor implements HandlerInterceptor {

    public List<String> includePattern =
            Arrays.asList("/users/*/update",
                    "/articles/create",
                    "/articles/*/update",
                    "/articles/*/comments/");

    public List<String> excludePattern =
            Arrays.asList();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        final HttpSession session = request.getSession();

        final Long userId = (Long) session.getAttribute(SessionData.USER_KEY);

        if (userId == null) {
            response.sendRedirect("/login");
            return false;
        }

        return true;
    }
}
