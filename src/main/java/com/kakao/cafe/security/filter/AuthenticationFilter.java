package com.kakao.cafe.security.filter;

import com.kakao.cafe.common.meta.SessionData;
import com.kakao.cafe.common.meta.URLPath;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class AuthenticationFilter extends OncePerRequestFilter {

    private final List<String> noAuthUrlPatterns;

    public AuthenticationFilter(List<String> noAuthUrlPatterns) {
        this.noAuthUrlPatterns = noAuthUrlPatterns;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        final String path = request.getServletPath();

        return noAuthUrlPatterns.stream()
                .anyMatch(path::matches);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final HttpSession session = request.getSession();

        if (hasNotSession(session) || hasNotUserKey(session)) {
            response.sendRedirect(URLPath.LOGIN_FORM.getPath());
            return;
        }

        filterChain.doFilter(request, response);
    }

    private boolean hasNotUserKey(HttpSession session) {
        return session.getAttribute(SessionData.USER_KEY) == null;
    }

    private boolean hasNotSession(HttpSession session) {
        return session == null;
    }


}
