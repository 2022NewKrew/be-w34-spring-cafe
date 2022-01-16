package com.example.kakaocafe.security.filter;

import com.example.kakaocafe.core.meta.SessionData;
import com.example.kakaocafe.core.meta.URLPath;
import com.example.kakaocafe.security.domain.CsrfTokenContext;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

public class CsrfVerificationFilter extends OncePerRequestFilter {

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        final HttpMethod requestMethod = HttpMethod.resolve(request.getMethod());
        return isNotPostAndPut(requestMethod);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final CsrfTokenContext csrfTokenContext = getCsrfTokenContext(request);
        validateCsrfToken(request, csrfTokenContext);

        filterChain.doFilter(request, response);
    }

    private CsrfTokenContext getCsrfTokenContext(HttpServletRequest request) {
        final HttpSession httpSession = request.getSession(false);
        if (httpSession == null)
            throw new RuntimeException();

        final CsrfTokenContext csrfTokenContext = (CsrfTokenContext) httpSession.getAttribute(SessionData.CSRF);
        if (csrfTokenContext == null)
            throw new RuntimeException();

        return csrfTokenContext;
    }

    private void validateCsrfToken(ServletRequest request, CsrfTokenContext csrfTokenContext) {
        final String csrfTokenStr = request.getParameter("_csrf");
        final UUID requestCsrfToken = UUID.fromString(csrfTokenStr);

        csrfTokenContext.validate(((HttpServletRequest) request).getRequestURI(), requestCsrfToken);
    }

    private boolean isNotPostAndPut(HttpMethod requestMethod) {
        return !(requestMethod == HttpMethod.POST || requestMethod == HttpMethod.PUT);
    }
}
