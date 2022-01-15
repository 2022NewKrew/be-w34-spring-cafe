package com.example.kakaocafe.security.filter;

import com.example.kakaocafe.core.meta.SessionData;
import com.example.kakaocafe.core.meta.URLPath;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class AuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        final String path = request.getServletPath();

        final boolean isCss = path.contains("/css/");
        final boolean isImages = path.contains("/images/");
        final boolean isFavicon = path.contains("/favicon.ico");

        final boolean isIndex = path.equals(URLPath.INDEX.getPath());
        final boolean isShowSignUpForm = path.equals(URLPath.SHOW_SIGN_UP_FROM.getPath());
        final boolean isSignUp = path.equals(URLPath.SIGN_UP.getPath());
        final boolean isShowLoginForm = path.equals(URLPath.SHOW_LOGIN_FROM.getPath());
        final boolean isLogin = path.equals(URLPath.LOGIN.getPath());

        return isLogin || isShowLoginForm || isIndex || isCss || isImages || isFavicon || isShowSignUpForm || isSignUp;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final HttpSession httpSession = request.getSession(false);

        if (hasNotSession(httpSession) || hasNotUserKey(httpSession)) {
            response.sendRedirect(URLPath.SHOW_LOGIN_FROM.getPath());
            return;
        }

        filterChain.doFilter(request, response);
    }

    private boolean hasNotUserKey(HttpSession httpSession) {
        return httpSession.getAttribute(SessionData.USER_KEY) == null;
    }

    private boolean hasNotSession(HttpSession httpSession) {
        return httpSession == null;
    }
}
