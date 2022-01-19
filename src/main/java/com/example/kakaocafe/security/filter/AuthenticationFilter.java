package com.example.kakaocafe.security.filter;

import com.example.kakaocafe.core.meta.SessionData;
import com.example.kakaocafe.core.meta.URLPath;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

public class AuthenticationFilter extends OncePerRequestFilter {

    private final List<URLPath> noAuthUrlPatterns;

    public AuthenticationFilter(List<URLPath> noAuthUrlPatterns) {
        this.noAuthUrlPatterns = noAuthUrlPatterns;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        final HttpMethod method = HttpMethod.resolve(request.getMethod());
        final String url = request.getServletPath();

        return noAuthUrlPatterns.stream()
                .anyMatch(urlPath -> urlPath.isSameUrlAndMethod(url, method));
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final HttpSession httpSession = request.getSession(false);

        if (hasNotSession(httpSession) || hasNotUserKey(httpSession)) {
            response.sendRedirect(URLPath.SHOW_LOGIN_FORM.getPath());
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
