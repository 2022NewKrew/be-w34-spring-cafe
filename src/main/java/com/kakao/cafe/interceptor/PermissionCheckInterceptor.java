package com.kakao.cafe.interceptor;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

@Getter
public class PermissionCheckInterceptor implements HandlerInterceptor {
    private final List<String> checkUrl = Arrays.asList("/questions/**");
    private Logger logger = LoggerFactory.getLogger(PermissionCheckInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(RequestMethod.GET.name().equals(request.getMethod())) {
            return true;
        }

        HttpSession session = request.getSession();
        String userName = (String)session.getAttribute("sessionUserName");
        String writer = request.getParameter("writer");

        if(userName == null || !userName.equals(writer)) {
            response.sendRedirect("/error/permission_fail.html");
            return false;
        }
        return true;
    }
}
