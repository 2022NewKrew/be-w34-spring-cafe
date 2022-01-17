package com.example.kakaocafe.security.filter;

import com.example.kakaocafe.core.meta.URLPath;
import com.example.kakaocafe.security.exception.CsrfException;
import org.springframework.http.HttpHeaders;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ExceptionHandlerFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (CsrfException csrfException) {
            logger.error("csrf에러", csrfException);
            final String redirectUrl = request.getHeader(HttpHeaders.REFERER);
            response.sendRedirect(redirectUrl);
        } catch (Exception exception) {
            logger.error("알 수 없는 에러", exception);
            response.sendRedirect(URLPath.SHOW_ERROR_404.getPath());
        }
    }
}
