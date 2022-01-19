package com.kakao.cafe.controller.interceptor;

import com.kakao.cafe.error.ErrorCode;
import com.kakao.cafe.error.exception.ForbiddenAccessException;
import com.kakao.cafe.persistence.model.AuthInfo;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

public class AuthCheckInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(AuthCheckInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
        Object handler) {

        if (request.getRequestURI().equals("/users")
            && !request.getMethod().equals("PUT")) {
            return true;
        }

        logger.info("Auth Info Check for {} {}", request.getMethod(), request.getRequestURI());

        HttpSession session = request.getSession();
        AuthInfo authInfo = (AuthInfo) session.getAttribute("auth");
        if (authInfo == null) {
            throw new ForbiddenAccessException(ErrorCode.FORBIDDEN_ACCESS, request.getRequestURI());
        }

        return true;
    }
}
