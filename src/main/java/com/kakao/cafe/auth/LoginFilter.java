package com.kakao.cafe.auth;

import com.kakao.cafe.login.SessionConfig;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LoginFilter implements Filter {

    private static final List<String> whiteList = Arrays.asList("/", "/users/login", "/users/form");


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String requestURI = httpRequest.getRequestURI();

        try {
            if(!checkWhiteList(requestURI)) {
                HttpSession session = httpRequest.getSession(false);
                if (session == null || session.getAttribute(SessionConfig.LOGIN_COOKIE) == null) {
                    httpResponse.sendRedirect("/users/login?redirectURL=" + requestURI);
                    return;
                }
            }
            chain.doFilter(request, response);
        } catch (Exception e) {
            throw e;
        }
    }

    private boolean checkWhiteList(String requestURI) {
        return whiteList.contains(requestURI);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
