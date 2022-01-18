package com.kakao.cafe.global.security.filter;

import com.kakao.cafe.global.error.exception.NoSessionException;
import com.kakao.cafe.global.meta.RequestPath;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class SessionFilter extends OncePerRequestFilter {

    private final List<RequestPath> noAuthUrlPatterns;

    public SessionFilter(List<RequestPath> noAuthUrlPatterns) {
        this.noAuthUrlPatterns = noAuthUrlPatterns;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        System.out.println("request.getServletPath(): " + request.getServletPath());
        return noAuthUrlPatterns.stream()
                .anyMatch(requestPath -> matchRequest(request, requestPath));
    }

    private boolean matchRequest(HttpServletRequest request, RequestPath requestPath) {
        return request.getMethod().equals(requestPath.getMethod().name()) && requestPath.matchPath(request.getServletPath());
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        HttpSession session = request.getSession();

        Object value = session.getAttribute("sessionedUser");
        if (value == null) { response.sendRedirect("/users/login/form"); }

        filterChain.doFilter(request, response);
    }
}
