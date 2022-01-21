package com.kakao.cafe.global.security.filter;

import com.kakao.cafe.global.meta.RequestPath;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class SessionFilter extends OncePerRequestFilter {

    // 로그인 session을 요구하지 않을 request white list
    private final List<RequestPath> noAuthUrlPatterns;

    public SessionFilter(List<RequestPath> noAuthUrlPatterns) {
        this.noAuthUrlPatterns = noAuthUrlPatterns;
    }

    // 필터 제외
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return noAuthUrlPatterns.stream()
                .anyMatch(requestPath -> matchRequest(request, requestPath));
    }

    // method(GET, POST...) 일치 && path matching
    private boolean matchRequest(HttpServletRequest request, RequestPath requestPath) {
        return request.getMethod().equals(requestPath.getMethod().name()) && requestPath.matchPath(request.getServletPath());
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        HttpSession session = request.getSession();

        // session이 없으면 로그인 페이지로 redirect
        Object value = session.getAttribute("sessionedUser");
        if (value == null) { response.sendRedirect("/users/login/form"); }

        filterChain.doFilter(request, response);
    }
}
