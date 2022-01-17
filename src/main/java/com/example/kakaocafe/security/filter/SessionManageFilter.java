package com.example.kakaocafe.security.filter;

import com.example.kakaocafe.core.meta.SessionData;
import com.example.kakaocafe.security.domain.CsrfTokenContext;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

public class SessionManageFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        HttpSession httpSession = request.getSession(false);
        if (Objects.isNull(httpSession)) {
            httpSession = request.getSession();
            httpSession.setAttribute(SessionData.CSRF, new CsrfTokenContext());
        }

        filterChain.doFilter(request, response);
    }
}
