package com.kakao.cafe.user.config;

import com.kakao.cafe.user.repository.SessionRepository;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.HandlerInterceptor;

public class AuthInterceptor implements HandlerInterceptor {

    private final SessionRepository sessionRepository;

    public AuthInterceptor(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
        Object handler) throws Exception {
        if (request.getMethod().matches(HttpMethod.OPTIONS.name()) || process(request)) {
            return true;
        }
        response.sendRedirect("/user/login.html");
        return false;
    }

    private boolean process(HttpServletRequest request) {
        UUID sessionId = (UUID) request.getSession().getAttribute("sessionId");
        return sessionRepository.exist(sessionId);
    }
}
