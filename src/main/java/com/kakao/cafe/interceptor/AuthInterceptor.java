package com.kakao.cafe.interceptor;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.exception.UnauthenticatedException;
import com.kakao.cafe.exception.UnauthorizedException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User sessionedUser = (User) request.getSession().getAttribute("sessionedUser");
        if (sessionedUser == null) {
            throw new UnauthenticatedException();
        }

        Map<String, String> pathVariables = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        if (Integer.parseInt(pathVariables.get("id")) != sessionedUser.getId()) {
            throw new UnauthorizedException();
        }

        return true;
    }
}
